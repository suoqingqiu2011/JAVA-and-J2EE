package com.ubp.l3info.ftfmyapp.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.ubp.l3info.ftfmyapp.msg.MainActivity;

public class TalkServer {
	
	public static String clientchat;
	static final int portNo = 3333;
	public static void main(String args[]) throws IOException{
		
		ServerSocket server = new ServerSocket(portNo); /* la porte de serveur  */
		System.out.println("Success! Serveur!");
			
		Socket clientsocket= server.accept(); /* Attendre de connecter la porte */
		
		ServerThread ST = new ServerThread(clientsocket);
		//serverchat = ST.s_sin.readLine();	
		clientchat = ST.s_is.readLine();   /* Obtenir des strings des flots qui viennet du client */
	}
}
			
			
class ServerThread extends Thread{	

			private Socket socket;
			
			BufferedReader s_is;
			BufferedReader s_sin;
		    private PrintWriter s_os; 
			
			public ServerThread(Socket server) throws IOException
		    {
				try{
				
					socket = server;
				
					s_is = new BufferedReader(new InputStreamReader(socket.getInputStream()));/*Lire des input flots qui viens du client dans Buffer*/
					
					s_os = new PrintWriter(socket.getOutputStream()); /* Fournir une print pour des output flots et formatage  */
					
					s_sin = new BufferedReader(new InputStreamReader(socket.getInputStream(),MainActivity.TypetextEditor.getText().toString())); /*Lire des input flots tapes par le server(this) dans Buffer */
					//s_sin = new BufferedReader(new InputStreamReader(System.in)); 
					
					
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
					//String line = s_sin.readLine();
					
					new MainActivity();
					String line = MainActivity.TypetextEditor.getText().toString(); /* Obtenir des contexts d'input */
					
					while(!line.equals("end")){
		
						//s_os.println(line);
						
						s_os.flush();
						
						new MainActivity().receiveMessage(s_is.readLine());
						//new MainActivity()).sendMessage(line);
						
						//System.out.println("Server:"+line);
						
						//System.out.println("Client:"+s_is.readLine());	
						
					} 
						
						s_os.close(); 
						s_is.close(); 
						socket.close();
					
				}
				catch (IOException e)
				{
				e.printStackTrace();
				}
				
			}
}


