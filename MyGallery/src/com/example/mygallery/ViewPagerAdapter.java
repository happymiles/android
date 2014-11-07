package com.example.mygallery;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
 
public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Bitmap[] imgs;
    String filepath[];
    public ViewPagerAdapter(Context context,String[] filepath,Bitmap[] imgs) {
        this.context = context;
        this.imgs = imgs;
        this.filepath = filepath;
    }
 
    @Override
    public int getCount() {
        return imgs.length;
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
    TouchImageView imgflag;
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
 
        
        
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);
 
        imgflag = (TouchImageView) itemView.findViewById(R.id.flag);
        //imgflag.setImageBitmap(imgs[position]);
        
        imgflag.setImageURI(Uri.fromFile(new File(ImageAdapter.filepath[position])));
        //imgflag.setImageBitmap(BitmapFactory.decodeFile(filepath[position]));
        ((ViewPager) container).addView(itemView);
 
        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}