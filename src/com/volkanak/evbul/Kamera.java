package com.volkanak.evbul;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Kamera extends Activity {
	private static final int CAMERA_REQUEST = 1888;
	private ImageView Resim;
	TextView baslik;
	TextView konum;
	TextView fiyat;
	File file ;
	final AsyncHttpClient client = new AsyncHttpClient();
	// dosya gönderirken dosyayý iliþtireceðimiz nesne.
	final RequestParams params = new RequestParams();
	final RequestParams veri = new RequestParams();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kamera);
		
        Button photoButton = (Button) this.findViewById(R.id.button1);
        Resim = (ImageView)this.findViewById(R.id.imageView1);
        baslik=(TextView)findViewById(R.id.baslik);
        konum=(TextView)findViewById(R.id.konum);
        fiyat=(TextView)findViewById(R.id.fiyat);
        
        
        photoButton.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
        // Kamerayý açacak ve kullanýmýný saðlayacak bir form açýlýyor
        // Bu form bir istek koduyla çaðrýlýyor
        Intent cameraIntent = new Intent(android.provider.MediaStore
                                              .ACTION_IMAGE_CAPTURE);
         startActivityForResult(cameraIntent, CAMERA_REQUEST);

	}
});
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
        
        // Gönderilen istek kodu kontrol ediliyor
        // Eðer fotoðraf çekimi için gönerilen istekse kabul ediliyor ve
        // bu form bir deðer döndürecek biçinde çaðrýldýðý için
        // kamerayla çekilen fotoðrafý bize döndürecektir.
        // Bu resim iþleniyor ve resim görüntüleyici bileþene atanýyor
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) { 
        
             Bitmap photo = (Bitmap) data.getExtras().get("data");
             Resim.setImageBitmap(photo);
             
            /* 
             
             try{
            	
            	 File resimd = new File( Environment.getExternalStorageDirectory()+File.separator + "volkan.jpg");
            	 FileOutputStream outptOs = new FileOutputStream(resimd );
            	 photo.compress( Bitmap.CompressFormat.JPEG, 100, outptOs );
            	 
            	 //getApplicationContext().setWallpaper(photo);
            	 }catch (IOException e) {
            	 // TODO: handle exception
            	 e.printStackTrace();
            	 }
            	 */
          
            	
             file = new File(Environment.getExternalStorageDirectory()+File.separator + "deneme.jpg");
             try {
            	 FileOutputStream outptOs = new FileOutputStream(file );
            	 photo.compress( Bitmap.CompressFormat.JPEG, 100, outptOs ); 
            	 params.put("dosya", file);
     
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
         	client.post("http://akakademi.biz/resim/yukle.php", params, new AsyncHttpResponseHandler());
     	    Log.d("gonderdi",file.getName());
            
         } 
       
     }
    
    public void ekle (View v) {
    	
    
    	veri.put("baslik",baslik.getText().toString());
    	veri.put("konum",konum.getText().toString());
    	veri.put("fiyat",fiyat.getText().toString());
    	veri.put("resim","http://akakademi.biz/resim/deneme.jpg");
	    	
	    	client.get("http://akakademi.biz/resim/urunekle1.php", veri, new AsyncHttpResponseHandler()
	    	
	    	{
	    		
	    		public void onSuccess(int statusCode, org.apache.http.Header[] headers, String content) {
	    			Toast.makeText(getApplicationContext(),"ÜRÜNLER VERÝTABANINA KAYDEDÝLDÝ",Toast.LENGTH_LONG).show();
	    			
	    		};
	    		
	    		public void onFailure(int statusCode, Throwable error, String content) {
	    			Toast.makeText(getApplicationContext(),"Hataaaaaaaaaa",Toast.LENGTH_LONG).show();
	    		};
	    	}
	    			
	    			);
	 		
            
	   startActivity(new Intent(getApplicationContext(),Ana.class));   		
	 		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
