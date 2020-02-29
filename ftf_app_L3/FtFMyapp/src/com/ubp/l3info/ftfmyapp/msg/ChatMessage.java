package com.ubp.l3info.ftfmyapp.msg;


public class ChatMessage {  
	  
    public static final int MESSAGE_FROM = 0;  
    public static final int MESSAGE_TO = 1;  
  
    private int direction;  
    private String content;  
  
    public ChatMessage(int direction, String content) {  
        super();  
        this.direction = direction;  
        this.content = content;  
    }  
  
    public int getDirection() {  
        return direction;  /* Par le moyenne de la fonction , obtenir le direction */
    }  
  
    public void setDirection(int direction) {  
        this.direction = direction;  /* Par le moyenne de la fonction , mettre le direction */
    }  
  
    public void setContent(String content) {  
        this.content = content;  /* Par le moyenne de la fonction , mettre le content */
    }  
  
    public CharSequence getContent() {  
        return content;  /* Par le moyenne de la fonction , obtenir le content */
    }  
    
}  

