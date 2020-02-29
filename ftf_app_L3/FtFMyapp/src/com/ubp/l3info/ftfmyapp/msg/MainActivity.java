package com.ubp.l3info.ftfmyapp.msg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import java.awt.Desktop; 
//import java.io.File; 
//import java.net.URI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ubp.l3info.ftfmyapp.map.Activitymap;
import com.ubp.l3info.ftfmyapp.msg.ChatMessage;
import com.ubp.l3info.ftfmyapp.R;
import com.ubp.l3info.ftfmyapp.friend.Mainfriend;
import com.ubp.l3info.ftfmyapp.socket.TalkClient;
import com.ubp.l3info.ftfmyapp.socket.TalkServer;


public class MainActivity extends Mainfriend {
	protected static final String TAG = "PrinciplChat"; /*Mettre le static label */
	private ChattingAdapter chatHistoryAdapter; /**/
	//private static Desktop desktop; 
	private List<ChatMessage> messages = new ArrayList<ChatMessage>(); /*La liste pour stocker l'histoire de chat*/
	//private List<ChatMessage> dates = new ArrayList<ChatMessage>(); 
	private ListView chatHistoryLv;
	private Button sendBtn;
	private Button friendBtn;
	private Button mapBtn;
	public static EditText TypetextEditor;
	private TextView pername;
	//private TextView etmyname;
	protected TextView friendname;
	protected TextView username1;

	

	@Override
	public void onCreate(Bundle savedInstanceState) {  		
		super.onCreate(savedInstanceState);  	
		setContentView(R.layout.chatting);  
		 
		   
		chatHistoryLv = (ListView) findViewById(R.id.chatting_history_lv);  
		setAdapterForThis();  
	
		friendBtn = (Button) findViewById(R.id.friend_button);
		//friendBtn.setOnClickListener(this);
		
		sendBtn = (Button) findViewById(R.id.send_button);
		
		mapBtn = (Button) findViewById(R.id.Button_map);
		
		TypetextEditor = (EditText) findViewById(R.id.text_editor);  /*Les contexts que Users ont tapes*/
		//sendBtn.setOnClickListener(l);
		
		
		Intent intent = getIntent();
		String get_index = intent.getStringExtra("per_index"); /*Class MainActivity herite une valeur de Class Mainfriend pour obtenir le string de item[] choisi*/
		
		//String items[] = getResources().getStringArray(R.array.select_friend);
		
		pername =  (TextView) findViewById(R.id.chatting_contact_name);		
		//String myrnameheader = new Mainlogin().name.getText().toString();
		pername.setTextColor(0xffff33cc);
		pername.setText("To"+" : "+"< "+get_index+" >"); /*A la tete de UI de Chatting, les 'friends' auxquels nous voulons communiquer*/
		//username1.setText("Welcome"+ Arrays.toString(items));
		
		
		mapBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View vvv) {
				
				startActivity(new Intent(MainActivity.this, Activitymap.class));
				finish();
				}
			});
		
		
		
		new TalkServer();
		String receiveStr = TalkServer.clientchat;
		receiveMessage(receiveStr);  /* Lorsque nous sommes le server. Ici ce sont ceux que le server obtenir des messages de clients dans Class TalkServer */
		
		/*new TalkClient();
		String receiveStr = TalkClient.serverchat;
		receiveMessage(receiveStr);*/       /*Si nous sommes des clients, des message sont ceux qui viennent du server*/
	
		sendBtn.setOnClickListener(new Button.OnClickListener(){
    			public void onClick(View vv) {
		
    				if (vv.getId() == sendBtn.getId()) {   /* id de 'View' == id de 'sendBtn' dans xml . Ce peut etre omis*/
    					String str = TypetextEditor.getText().toString(); 
    					String sendStr;
    					
    					if (str != null && (sendStr = str.trim().replaceAll("\r", "")
    						.replaceAll("\t", "").replaceAll("\n", "").replaceAll("\f", "")) != "") /*Eviter des cas ayant plus de space*/
    					{	
    						SimpleDateFormat sysdate = new SimpleDateFormat("yyyy-MM-dd (hh:mm:ss)");       
    		        		String  timestr = sysdate.format(new Date(System.currentTimeMillis())); /*Obtenir du temps du systeme*/ 
    		        		
    						sendMessage(timestr+"\n"+sendStr); /*Transmettre des valeurs dans sendMessage()*/   
    						
    					}
    					TypetextEditor.setText("");
    					}
    				}
    			/* msg example*/
    			private void sendMessage(String sendStr) {
    				messages.add(new ChatMessage(ChatMessage.MESSAGE_TO, sendStr)); /*le string de sendStr est donne dans la variable 'MESSAGE_TO' de Class ChatMessage */
    				chatHistoryAdapter.notifyDataSetChanged(); /*La fonction est pour renouveler l'histoire de chat a tout moment.*/
    			}	
    			
    			
    			
    			//private void sendDate(String timetr) {
    			//	messages.add(new ChatMessage(ChatMessage.MESSAGE_TO, timestr)); /*le string de sendStr est donne dans la variable 'MESSAGE_TO' de Class ChatMessage */
    			//	dateHistoryAdapter.notifyDataSetChanged(); /*La fonction est pour renouveler l'histoire de chat a tout moment.*/
    			//}	
		});	
    		
		
		
		
		friendBtn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, Mainfriend.class));
				finish(); /* Retourner le UI de Mainfriend*/
			}

		});

	}
		
	

		public void receiveMessage(String receiveStr) {
		while(receiveStr != null){		
			messages.add(new ChatMessage(ChatMessage.MESSAGE_FROM, receiveStr)); /*le string de receiveStr est donne dans la variable 'MESSAGE_FROM' de Class ChatMessage */
			chatHistoryAdapter.notifyDataSetChanged();
			}
			
	}



		/* mettre Adapter pour l' histoire de 'chat' */  
				private void setAdapterForThis() {  
					//initMessages();  
					chatHistoryAdapter = new ChattingAdapter(MainActivity.this, messages);  /* La variable 'messages' de Class MainActivity est mise dans le cas concret de Class ChattingAdapter*/
					chatHistoryLv.setAdapter(chatHistoryAdapter);   /* Ici ,'ListView' affiche les resultats de l'histoire de chat  */
					
					}

				
				
				
			/*ajouter msg pour tester des codes, ca arrivera ou pas avec ChattingAdapter et ChatMessage */
				
				/*private void initMessages() {  
					//new TalkClient();
					messages.add(new ChatMessage(ChatMessage.MESSAGE_FROM, "hello"));  
					messages.add(new ChatMessage(ChatMessage.MESSAGE_TO, "hello"));  
					messages.add(new ChatMessage(ChatMessage.MESSAGE_FROM, "welcome to my app ftf!!!"));  
					//messages.add(new ChatMessage(ChatMessage.MESSAGE_FROM, TalkClient.serverchat));  
					//messages.add(new ChatMessage(ChatMessage.MESSAGE_TO, TalkClient.clientchat)); 
					}  */
				
				
    
}