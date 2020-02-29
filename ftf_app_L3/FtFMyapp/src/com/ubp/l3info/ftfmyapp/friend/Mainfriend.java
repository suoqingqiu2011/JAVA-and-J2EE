package com.ubp.l3info.ftfmyapp.friend;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ubp.l3info.ftfmyapp.R;
import com.ubp.l3info.ftfmyapp.msg.MainActivity;

public class Mainfriend extends Activity {
	private TextView myname;
	private Button friendlistbtn;
	protected int index; /**/
	
	public final static int REQUEST_CODE = 1; 
	
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainfriend);
		
		
		Intent get_login_intent = getIntent();
		String get_name = get_login_intent.getStringExtra("etname");/*Class Mainfriend herite une valeur de Class Mainlogin*/
		
		myname = (TextView) findViewById(R.id.chatting_username);
		myname.setText("Welcome \n" +"< "+ get_name +" >"); /*Deposer le 'username' dans le UI de Mainfriend*/
		
		
		friendlistbtn = (Button) findViewById(R.id.friend_list_button); 
		//friendlistbtn.setOnClickListener(this);


		friendlistbtn.setOnClickListener(new Button.OnClickListener(){
			
			public void onClick(View v) {
					
				AlertDialog.Builder ABfriendlist = new AlertDialog.Builder(Mainfriend.this);  /*Supposons un cas concret 'ABfriendlist' pour afficher la liste de friends*/
				
				LayoutInflater inflater = getLayoutInflater();
				View layout1 = inflater.inflate(R.layout.friend_tab,(ViewGroup) findViewById(R.id.dialog1)); /* Utiliser 'LayoutInflater' pour le 'View' dynamique */
				
				ABfriendlist.setIcon(R.drawable.ic_launcher);
				ABfriendlist.setTitle(R.string.friend_choice).setView(layout1);/*Ajouter le icon et le titre a la tete*/
				
				
				
				ABfriendlist.setSingleChoiceItems(R.array.select_friend, 0, new DialogInterface.OnClickListener() { /* Realiser la liste de choix 'items' */

					public void onClick(DialogInterface dialog, int whichButton) {
						 
                             index = whichButton;  /* 'whichbutton' est  ce que Users ont clique'*/
                         }  
					
				});
				
				
				ABfriendlist.setPositiveButton(R.string.friend_ok, new DialogInterface.OnClickListener() { /* Une action d'ecoute est integre directement dans le 'positivebutton' */
					
				
					String items[] = getResources().getStringArray(R.array.select_friend); /*Utiliser getResource() pour prendre tous les strings de items dans 'array.xml' */
					
					
					public void onClick(DialogInterface dialog, int whichButton) {
					/* left click */
						final AlertDialog friendchoice = new AlertDialog.Builder(Mainfriend.this).setMessage(
		                          "Your choice is" + " : " + items[index]).show(); /*Afficher le item que 'index = whichButton'. C'est un type de 'String'*/
						
						Handler hander = new Handler();  /* Supposons un nouveau Thread. Donc, utiliser un cas concret de Handler '' */
		                  Runnable runnable = new Runnable() 
		                  {
		                     @Override
		                     public void run(){
		                    	 friendchoice.dismiss();
		                     }
		                  };
		                  hander.postDelayed(runnable, 3 * 1000);
						
		                Intent intent1 = new Intent();
		                intent1.setClass(Mainfriend.this , MainActivity.class);
		                intent1.putExtra("per_index", items[index]);
		                startActivityForResult(intent1,REQUEST_CODE);
		                
							
						}

				});
				 
				
			
				ABfriendlist.setNegativeButton(R.string.friend_cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				/* right click */
					final AlertDialog friendchoice = new AlertDialog.Builder(Mainfriend.this).setMessage(

	                          "You haven't chosen a friend yet !").show();
	                
	                  /* 3s shutdown */

	                  Handler hander = new Handler();
	                  Runnable runnable = new Runnable()
	                  {
	                     @Override
	                     public void run(){
	                    	 friendchoice.dismiss(); 
	                     }
	                  };
	                  hander.postDelayed(runnable, 3 * 1000); /* Controler du temps de 'Runnable', pendant 3 secondes*/
				}
				});
				ABfriendlist.create();/* Creer  l'AlertDialog 'ABfriendlist' */
				ABfriendlist.show();
			}
		});
			
	}
		
}
