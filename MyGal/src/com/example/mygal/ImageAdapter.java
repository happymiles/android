package com.example.mygal;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	Context context;
	ProgressDialog mProgressDialog;
	static Bitmap[] mThumbIds = null;
	static String[] filepath = null;
	int count;
	public ImageAdapter(Context context,String url,String group_id) {
		this.context = context;
		if(ImageSource.cache.cache.size()==0)
		{
			Log.e("Size",ImageSource.cache.cache.size()+"");
			ImageSource.load(url,group_id);
		}
	}

	public int getCount() {
		return ImageSource.cache.cache.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		imageView = new ImageView(context);
		imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setPadding(8, 8, 8, 8);
		imageView.setImageBitmap(ImageSource.cache.get(position+""));
		return imageView;
	}
}