package com.ubp.l3info.ftfmyapp.msg;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ubp.l3info.ftfmyapp.msg.ChatMessage;
import com.ubp.l3info.ftfmyapp.R;
  
public class ChattingAdapter extends BaseAdapter {  
    protected static final String TAG = "ChattingAdapter";  
    private Context context;  
    private List<ChatMessage> chatMessages;   
    
                                            
    public ChattingAdapter(Context context, List<ChatMessage> messages) {  
        super();  
        this.context = context;  
        this.chatMessages = messages;  
        
    }  

      
    public int getCount() {                /*Obtenir le taille de Liste*/
        return chatMessages.size();  
    }  
  
    
    public Object getItem(int position) {   
        return chatMessages.get(position);  /*Obtenir le valeur du position de 'chatMessages'*/  
    }  
  
     
    public long getItemId(int position) {   
        return position;  /*Par le moyenne de la fonction , obtenir le position */
    }  
  
                                  
    public View getView(int position, View convertView, ViewGroup parent) {  
        ViewHolder holder = null;  /* Utiliser ViewHolder pour stocker View et le reutiliser apres */
        ChatMessage message = chatMessages.get(position);  
     
        if (convertView == null || (holder = (ViewHolder) convertView.getTag()).flag != message.getDirection()) {  /*Integrer forcement un valeur pour 'convertView'*/
  
            holder = new ViewHolder();  
            if (message.getDirection() == ChatMessage.MESSAGE_FROM) {  /*Si le localisation est celui de 'MESSAGE_FROM'*/
                
            	holder.flag = ChatMessage.MESSAGE_FROM;  
                convertView = LayoutInflater.from(context).inflate(R.layout.chatting_item_from, null);  /*LayoutInflater va uitiliser le layout 'chatting_item_from.xml'*/
            
            } else {  
            	
                holder.flag = ChatMessage.MESSAGE_TO;  /*Les autres sont ceux de 'MESSAGE_TO'*/
                convertView = LayoutInflater.from(context).inflate(R.layout.chatting_item_to, null);  
              
            }  
  
            holder.text = (TextView) convertView.findViewById(R.id.chatting_content_itv);  /*La varialble text de 'ViewHolder' utilise la position de 'chatting_content_itv'*/
            
    		convertView.setTag(holder); /*View est depose forcement par des valeurs stockes dans 'ViewHolder' */
    		
 
           
        }  
        
        holder.text.setText(message.getContent());  /*Obtenir les flots de 'message'*/
    
        return convertView;  
    }  
    
  
    static class ViewHolder {  

        public TextView Datetime;
		TextView text;  
        int flag;  
    }  
  
}  