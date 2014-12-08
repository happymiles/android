package com.example.happymiles;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreenAdapter extends BaseAdapter{

	Context context;
	String urlList[];
	String descriptions[];
	public HomeScreenAdapter(Context context,String urlList[],String descriptions[]) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.urlList = urlList;
		this.descriptions = descriptions;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return urlList.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.homescreenitem, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		
		try {
			imageView.setImageBitmap(BitmapFactory.decodeStream(new URL(urlList[arg0])
			.openStream()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textView.setText(descriptions[arg0]);
		return view;
	}

}
