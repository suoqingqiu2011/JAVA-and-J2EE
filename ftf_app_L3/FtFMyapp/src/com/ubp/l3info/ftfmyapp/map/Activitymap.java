package com.ubp.l3info.ftfmyapp.map;


import java.io.IOException;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;
import com.ubp.l3info.ftfmyapp.R;
import com.ubp.l3info.ftfmyapp.msg.MainActivity;

public class Activitymap extends Activity implements OnClickListener{
	private WebView webView;
	private Button rebutton;
	private String url = "https://www.google.fr/maps";
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        webView = (WebView) this.findViewById(R.id.web);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);/*Reglager des attributs et permettre d'executer JavaScript*/
     
       new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                 webView.loadUrl(url);
                 
            }
        }).start();

       /* MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
        
        rebutton = (Button) findViewById(R.id.Button_chat);
        rebutton.setOnClickListener(this);
	}
	
	 //public boolean onKeyDown(int keyCode, KeyEvent event) {  
	      //  if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {  
	     //       webView.goBack(); /*goBack() pour retourner la page precedente de WebView */ 
	    //        return true;  
	    //    }  
	    //    return false;  
	  //  }  
	 
        public void onClick(View v) {
        	
        	startActivity(new Intent(Activitymap.this, MainActivity.class));
			finish();;
			}
}
