package com.ubp.l3info.ftfmyapp.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.ubp.l3info.ftfmyapp.msg.MainActivity;


public class TalkClient {
	
	public static String clientchat;
	public static String serverchat;
	public static void main(String args[]) throws IOException {
		
		String addr = InetAddress.getLocalHost().getHostAddress();
		//InetAddress addr = InetAddress.getByName(ipaddr);
		
		//String ipaddr = InetAddress.getLocalHost().getHostAddress(); /* Prendre l'address de 'localhost' */
		//InetAddress addr = InetAddress.getByName(ipaddr);
		
		System.out.println("Success! Client!"+addr);
		
		ClientThread CT= new ClientThread(addr); /* Donner l'address pour sous_class ClientThread */
		//clientchat = CT.c_sin.readLine();
		
		serverchat = CT.c_is.readLine(); /* Obtenir des strings des flots qui viennet du client */
		
	}
}
		
class ClientThread extends Thread 
{	
	BufferedReader c_is;
	private PrintWriter c_os;
	BufferedReader c_sin;
	private Socket socket; 
	public static int portNo = 3333;
	
	public ClientThread(String addr)
	{
		try{
				
		socket=new Socket(addr,portNo); /* Utiliser l'address et la porte pour connecter le serveur */
		
		c_is = new BufferedReader(new InputStreamReader(socket.getInputStream()));/*Lire des input flots qui viens du serveur dans Buffer*/
		
		c_os = new PrintWriter(socket.getOutputStream()); /* Fournir une print pour des output flots et formatage  */
		
		//new MainActivity();
		c_sin = new BufferedReader(new InputStreamReader(socket.getInputStream(),MainActivity.TypetextEditor.getText().toString()));/*Lire des input flots tapes par le Client(this) dans Buffer*/
		//c_sin = new BufferedReader(new InputStreamReader(System.in));
		
	
		start();
		}
		catch(IOException e)
		{
		e.printStackTrace();
		}
		}
	
	public void run()
	{
		try
		{
			//String readline = c_sin.readLine(); 
			new MainActivity();
			String readline = MainActivity.TypetextEditor.getText().toString(); /* Obtenir des contexts d'input */
			
		while(!readline.equals("end"))
		{
		
			//c_os.println(readline);
			c_os.flush();
			
			new MainActivity().receiveMessage(c_is.readLine());
			//new MainActivity().sendMessage(readline);
			
			//System.out.println("Client:"+readline);
			//System.out.println("Server:"+c_is.readLine());
			//readline=c_sin.readLine() ;
				
		}
				
			c_os.close(); 
			c_is.close(); 
			socket.close();
	
		}
	
		catch(Exception e)
		{
			
		e.printStackTrace();
		System.out.println("Error"+ e);
			
		}
	}
	
	public  String getclientchat() throws IOException{
		//return c_sin.readLine();
		new MainActivity();
		String readline = MainActivity.TypetextEditor.getText().toString(); 
		return readline;
	}
}