
/*
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.Socket;  
import java.util.Scanner;  
  
public class Client {  
    public static void main(String[] args) {  
        Client mc = new Client();  
        mc.initClient();  
    }  
  
    public void initClient() {  
        try {  
            
            @SuppressWarnings("resource")
			Socket client = new Socket("localhost", 9090);  
           
            final InputStream ins = client.getInputStream();  
            final OutputStream ous = client.getOutputStream();  
           
            String msg = readMsg(ins);  
            System.out.println(msg);  
           
            String requestName = readMsg(ins);  
            System.out.println(requestName);  
            
            @SuppressWarnings("resource")
			final Scanner scanner = new Scanner(System.in);  
            String username = scanner.nextLine();  
            
            sendMsg(ous, username + "\r\n");  
            
            String requestPwd = readMsg(ins);  
            System.out.println(requestPwd);  
            
            String pwd = scanner.nextLine();  
          
            sendMsg(ous, pwd + "\r\n");  
            
            String result = readMsg(ins);  
            
            while(!result.equals("ok")){  
                 
                String message=readMsg(ins);  
                System.out.println(message);  
               
                message=readMsg(ins);  
                System.out.println(message);  
                
                message=readMsg(ins);  
                System.out.println(message);  
                
                username = scanner.nextLine();  
                
                sendMsg(ous, username + "\r\n");  
                
                message=readMsg(ins);  
                System.out.println(message);  
                
                pwd = scanner.nextLine();  
               
                sendMsg(ous, pwd + "\r\n");  
                
                result = readMsg(ins);  
            }  
              
            if (result.equals("ok")) {  
//              System.out.println("login success");  
                
                new Thread() {  
                    public void run() {  
                        try {  
                            while (true) {  
                                
                                String message = scanner.nextLine();  
                                sendMsg(ous, message + "\r\n");  
                            }  
                        } catch (Exception e) {  
                            e.printStackTrace();  
                        }  
                          
                    };  
                }.start();  
                  
                
                new Thread() {  
                    public void run() {  
                        try {  
                            while (true) {  
                                String message = readMsg(ins);  
                                System.out.println(message);  
                            }  
                        } catch (Exception e) {  
                            e.printStackTrace();  
                        }  
                    };  
                }.start();  
            } else {  
                System.out.println("fail login");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public String readMsg(InputStream ins) throws Exception {  
        int value = ins.read();  
        String str = "";  
        while (value != 10) {  
            
            if (value == -1) {  
                throw new Exception();  
            }  
            str = str + (char) value;  
            value = ins.read();  
        }  
        str = str.trim();  
        return str;  
    }  
  
    
    public void sendMsg(OutputStream ous, String str) throws Exception {  
        byte[] bytes = str.getBytes();  
        ous.write(bytes);  
        ous.flush();  
    }  
}  */

public class Client {
	public String innom;
	//private Serveur inserveur;
	public Client(String innom){
		this.innom=innom;
	}
	public String getnom(){
		return innom;
	}
	
	public boolean seConnceter(Serveur inserveur){
		if(inserveur.connecter(this)){
			System.out.println(innom+" Sucess connect");
			return true;
		}
		return false;
	}
	public void envoyer( Serveur inserveur, String inmessage){
		inserveur.diffuser(inmessage);
	}
		
	public void recevoir(String inmessage){
		System.out.println(this.innom+": "+inmessage);
	}
}



