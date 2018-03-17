package com.volkanak.evbul;

import java.security.PublicKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class Ana extends Activity {

	
	String[] Baslik,Konum,Fiyat,Resimler;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana);
        
        AsyncHttpClient myclient=new AsyncHttpClient();
        myclient.get("http://akakademi.biz/resim/goster1.php",new AsyncHttpResponseHandler() 
        {
        	public void onSuccess(int statusCode, String content) {
           
        		try {
					JSONObject js=new JSONObject(content);
					JSONArray jsa=js.getJSONArray("listem");
					//Toast.makeText(Ana.this,jsa.length()+"" ,Toast.LENGTH_SHORT).show();
					Resimler=new String[jsa.length()];
					Baslik=new String[jsa.length()];
					Fiyat=new String[jsa.length()];
					Konum=new String[jsa.length()];
					
					for (int i = 0; i < jsa.length(); i++) {
						JSONObject a=jsa.getJSONObject(i); 
						Baslik[i] =a.getString("baslik");
						Konum[i] =a.getString("konum");
						Fiyat[i] =a.getString("fiyat");
						Resimler[i] =a.getString("resim");

						
					}
					
					   OzelAdapter adap=new OzelAdapter(Ana.this, Resimler, Baslik, Fiyat, Konum);
				     ListView liste=(ListView)findViewById(R.id.listView1);
				   liste.setAdapter(adap);
				
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }  	
        }
        		
        		
        		);
       
        
     
    }
    
    public void resimekle(View V){
    	Intent git= new Intent(Ana.this,Kamera.class);
    	startActivity(git);
    }
   
}

