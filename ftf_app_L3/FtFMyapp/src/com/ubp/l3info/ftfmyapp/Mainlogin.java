package com.ubp.l3info.ftfmyapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ubp.l3info.ftfmyapp.friend.Mainfriend;


public class Mainlogin extends Activity implements OnClickListener{
	public TextView tvusername;
	private TextView tvpassword;
	private TextView resulttv;
	private ProgressDialog mPgrogressDialog;
	public EditText etname;
	public EditText etpassword;
	
	private Button button;
	
	public final static int REQUEST_CODE = 1;  /* Pour retourner les defferents resultats, ca depend des chiffres donnees. Ici, nous n'avons que depose un REQUEST_CODE */
	
		@Override
		public void onCreate (Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_mainlogin); /*Nous avons transforme le layout de 'xml' a le type 'view' pour afficher l'interface user principale*/
			
			button = (Button) findViewById(R.id.button1);
			button.setOnClickListener(this); /* Nous avons mis l'action de 'OnClickListener' pour ecouter le button de login*/
			}
	
			@Override
		public void onClick(View v) {
	
			showCustomDialog(); /* L'action de click guide l'etablissement de AlertDialog */
			
			}

		
		
		public void showCustomDialog() {
			AlertDialog.Builder ad = new AlertDialog.Builder(this); /*Supposons un cas concret 'ad'*/

			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.activity_maintab,(ViewGroup) findViewById(R.id.dialog));/* Il faut utiliser 'LayoutInflater' pour ajouter le deroulement dynamique du nouveau 'View'*/
			
			tvusername = (TextView) layout.findViewById(R.id.etName); /*La dispostion de 'username'*/
		    tvusername.setTextColor(0xffccffff);
		    
			tvpassword = (TextView) layout.findViewById(R.id.etPassword);  /*La dispostion de 'password'*/
		    tvpassword.setTextColor(0xffccffff);
		    
		    resulttv =  (TextView) layout.findViewById(R.id.tvresult); /*Montrer les situations de 'login of the users' */
		    
		    etname = (EditText) layout.findViewById(R.id.etName); /*Le zone ou users pourrient taper 'username'*/
		    etpassword = (EditText) layout.findViewById(R.id.etPassword); /*Le zone ou users pourrient taper 'password'*/
		    
		    
			ad.setTitle("Login as Google User").setView(layout); /*A la tete, nous avons mis un titre*/
			ad.setPositiveButton("Enter", new DialogInterface.OnClickListener(){ /* Une action d'ecoute est integre directement dans le 'positivebutton' */
				public void onClick(DialogInterface dialog, int i) {
					
					
					String userName = tvusername.getText().toString().trim(); 
					
					String passWord = tvpassword.getText().toString().trim(); /*Pour obtenir des noms et des mots de pass que Users tapent. Il faut prendre le type 'String'*/
					
					String rightuserName = new String("ftf@gmail.com");
					String rightuserName1 = new String("app@gmail.com");
					String rightpassWord = new String("123");
					String rightpassWord1 = new String("abc"); /* Nous supposons des valeurs par defauts pour username et password. Parce que nous n'avons pas reussi connecter Base de donne de Google+ */
					
					
			
					/* login choice */
					try{
					
						if((userName.equals(rightuserName)&& passWord.equals(rightpassWord))||
								(userName.equals(rightuserName1)&& passWord.equals(rightpassWord1))){ /*Comparer ceux que Users ont tape. Confirmer si ce sont correct ou pas */
							
							tvusername.setTextColor(0xff99cc33);
							tvpassword.setTextColor(0xff99cc33);
							
							resulttv.setTextColor(0xff99cc33);
							resulttv.setText("Successful entering! "+userName); /*Si ce sont les memes, il remplace le text */
							
	                        String etnom = etname.getText().toString();
	                        //String etmotdepass = etpassword.getText().toString();
	                        
	                        mPgrogressDialog();
	                        
							Intent intent = new Intent(); 
							intent.setClass(Mainlogin.this, Mainfriend.class); /* Realiser le transforme de UI*/
							
							intent.putExtra("etname", etnom);   /* Transmettre la valeur 'etnom' a la class de 'Mainfriend'*/
							//intent.putExtra("etmotdepass", etmotdepass);
							
							startActivityForResult(intent,REQUEST_CODE);
							finish();
							
							Toast.makeText(Mainlogin.this, "Successful entering!", Toast.LENGTH_LONG).show(); /*Montre des cas de Users*/
							
						}
						else{
							
							tvusername.setTextColor(0xffff6666);
							tvpassword.setTextColor(0xffff6666);
							
							resulttv.setTextColor(0xffff6666);
							resulttv.setText("Failure entering! Please try again ..."); /*Si ce sont pas de memes, remettre le text pour indiquer l'echec*/
							
							Toast.makeText(Mainlogin.this, "Failure entering! Please try again ...", Toast.LENGTH_LONG).show();
							
							showCustomDialog(); /* Retourner le 'AlertDialog'*/
										
						}
						
						
						Thread.sleep(3000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
		            
				}
			});
			

			ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){ /* Une action d'ecoute est integre directement dans le 'negativebutton' pour fermer le 'AlertDialog'*/
				 public void onClick(DialogInterface dialog, int i) {
		                dialog.dismiss();
		            }
			});
			
			ad.create(); /*Creer le 'AlertDialog'*/
		    ad.show();
			
		
		
		}

		
		public void mPgrogressDialog(){
				mPgrogressDialog =  new ProgressDialog(this);
	            mPgrogressDialog.setMessage("Please wait...");
	            mPgrogressDialog.show();
			}
		

	
	

			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.mainlogin, menu);
				return true;
			}
			
			
			
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				// Handle action bar item clicks here. The action bar will
				// automatically handle clicks on the Home/Up button, so long
				// as you specify a parent activity in AndroidManifest.xml.
				int id = item.getItemId();
				if (id == R.id.action_settings) {
					return true;
				}
				return super.onOptionsItemSelected(item);
				}
}
