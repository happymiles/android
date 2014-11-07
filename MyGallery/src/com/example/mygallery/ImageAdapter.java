package com.example.mygallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
     Context mContext;
     static Bitmap[] mThumbIds = null;
     static String[] filepath = null;
     int count;
    public ImageAdapter(Context c,Bitmap[] mThumbIds,String[] filepath,int count) {
        mContext = c;
        this.filepath = filepath;
        this.mThumbIds = mThumbIds;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(mThumbIds[position]);
        return imageView;
    }
}