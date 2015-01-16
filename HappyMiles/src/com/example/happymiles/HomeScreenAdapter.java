package com.example.happymiles;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreenAdapter extends BaseAdapter {

	static Bitmap images[];
	Context context;
	ArrayList<String> url,desc;
	public HomeScreenAdapter(Context context, ArrayList<String> url, ArrayList<String> desc) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.url = url;
		this.desc = desc;
		images = new Bitmap[url.size()];
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return url.size();
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
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.homescreenitem, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
		TextView textView = (TextView) view.findViewById(R.id.textView1);

		try {
			if (HomeScreenAdapter.images[arg0] == null)
			{
				Bitmap image = BitmapFactory.decodeStream(new URL(
						url.get(arg0).toString()).openStream());
				imageView.setImageBitmap(image);
				images[arg0]=image;
			}
			else
				imageView.setImageBitmap(images[arg0]);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textView.setText(desc.get(arg0).toString());
		return view;
	}
}
