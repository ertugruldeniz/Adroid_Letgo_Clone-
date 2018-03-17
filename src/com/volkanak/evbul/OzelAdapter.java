package com.volkanak.evbul;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OzelAdapter extends BaseAdapter{

	Context icerik;
	static LayoutInflater inflater=null;
	String resimler[];
	String[] baslik;
	String[] fiyat;
	String[] konum;
	
	public OzelAdapter(Context Icerik,String Resimler[],String[] Baslik,String[] Fiyat,String[] Konum) {
		
	this.baslik=Baslik;
	this.resimler=Resimler;
	this.fiyat=Fiyat;
	this.konum=Konum;
	this.icerik=Icerik;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return baslik.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return getItemId(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View satir=convertView;
		if(satir==null)
		{
			inflater=(LayoutInflater)
		icerik.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			satir=inflater.inflate(R.layout.teksatir, null);
			
		}
		
		ImageView img=(ImageView)satir.findViewById(R.id.imageView1);
		TextView Baslik=(TextView)satir.findViewById(R.id.textView1);
		TextView Konum=(TextView)satir.findViewById(R.id.textView2);
		TextView Fiyat=(TextView)satir.findViewById(R.id.textView3);
		
		Baslik.setText(baslik[position]);
		Konum.setText(konum[position]);
		Fiyat.setText(fiyat[position]);
		//img.setImageResource(resimler[position]);
		Picasso.with(icerik).load(resimler[position]).into(img);
		return satir;
	}
	
	
	
	

}
