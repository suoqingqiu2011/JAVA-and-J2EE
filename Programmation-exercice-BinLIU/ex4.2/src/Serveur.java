import java.util.ArrayList;
import java.util.List;

/*
import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.ArrayList;   
import java.io.InputStream;  
import java.io.OutputStream;  

  
class Myserver {  
      
    public static ArrayList<ServerThread>list =new ArrayList<ServerThread>();  
    public  void initServer() {  
          
        try {  
          
            @SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(9090);  
            System.out.println("Server begin......");  
          
            while(true){  
                Socket socket =server.accept();  
                System.out.println("Client can tape......");  
                
                ServerThread st = new ServerThread(socket);  
                st.start();  
               
                list.add(st);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
  
}  

class ServerThread extends Thread {  
	  
    public Socket socket;  
    public InputStream ins;  
    public OutputStream ous;  
  
    public ServerThread(Socket socket) {  
        this.socket = socket;  
    }  
  
    public void run() {  
        try {  
           
            ins = socket.getInputStream();  
            ous = socket.getOutputStream();  
            
            String msg = "welcome to bin's server !";  
            sendMsg(ous, msg);  
            
            String userinfo = "please input your name:";  
            sendMsg(ous, userinfo);  
            
            String userName = readMsg(ins);  
            
            String pwd = "please input your password:";  
            sendMsg(ous,  pwd);  
            
            String pass = readMsg(ins);  
            
            boolean falg = loginCheck(userName, pass);  
           
            while (!falg) {  
                msg="no";  
                sendMsg(ous, msg);  
                msg = "Fail to connect server......";  
                sendMsg(ous, msg);  
                msg = "please check your name and password and login again.....";  
                sendMsg(ous, msg);  
                msg = "please input your name:";  
                sendMsg(ous, msg);  
               
                userName = readMsg(ins);  
                
                msg = "please input your password:";  
                sendMsg(ous, msg);  
                
                pass = readMsg(ins);  
                falg = loginCheck(userName, pass);  
            }  
  
           
            msg="ok";  
            sendMsg(ous, msg);  
            
            msg = "successful connected..... you can chat with your friends now ......";  
            sendMsg(ous, msg);  
           
            msg=readMsg(ins);  
            System.out.println("Client receive£º"+msg);  
            
            while(!"bye".equals(msg)){  
                
                for (int i = 0; i <Myserver.list.size(); i++) {  
                    ServerThread st =Myserver.list.get(i);  
                    
                    if(st!=this){  
                    System.out.println("msg envoye......");  
                        sendMsg(st.ous, userName+"  is say:"+msg);  
                        System.out.println("Msg success......");  
                    }  
                }  
               
                msg=readMsg(ins);  
            }  
  
        } catch (Exception e) {  
            System.out.println("error client......");  
//          e.printStackTrace();  
        }  
      
        try {  
            ins.close();  
            ous.close();  
            socket.close();  
            
            Myserver.list.remove(this);  
        } catch (IOException e) {  

            e.printStackTrace();  
        }  
    }  
  
   
    public boolean loginCheck(String name, String pwd) {  
        if (name.equals("bin") && pwd.equals("bin") || name.equals("user") && pwd.equals("pwd")  
                || name.equals("liu") && pwd.equals("liu")) {  
  
            return true;  
        }  
        return false;  
    }  
  
  
    public void sendMsg(OutputStream os, String s) throws IOException {  
       
        byte[] bytes = s.getBytes();  
        os.write(bytes);  
        os.write(13);  
        os.write(10);  
        os.flush();  
  
    }  
  
  
    public String readMsg(InputStream ins) throws Exception {  
        
        int value = ins.read();  
       
        String str = "";  
        while (value != 10) {  
            
            if (value == -1) {  
                throw new Exception();  
            }  
            str = str + ((char) value);  
            value = ins.read();  
        }  
        str = str.trim();  
        return str;  
    }  
  
}  

public class Serveur{  
    
    public static void main(String[] args) {  
        Myserver ms = new Myserver();  
        ms.initServer();  
    }  
  
}  */
/* this is a fonction server, it's for construire all the */
public class Serveur {
	ArrayList <Client> cli = new ArrayList<Client> ();
	public Serveur(){
	
	}

	public boolean connecter(Client inclient) {
		cli.add(inclient);
		return true;
	}

	public void diffuser(String inmessage) {
		for (int i=0;i<((List<Client>) cli).size();i++) { 
			((List<Client>) cli).get(i).recevoir(inmessage);
    	} 
	}
}