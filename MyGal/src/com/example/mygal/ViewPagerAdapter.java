package com.example.mygal;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ViewPagerAdapter extends PagerAdapter {
	Context context;
	LayoutInflater inflater;
	Bitmap[] imgs;
	String filepath[];
	int layout ;
	int img;
	TouchImageView imgflag;
	
	public ViewPagerAdapter(Context context,int layout,int img) {	
		this.context = context;
		this.img = img;
		this.layout = layout;
	}
	
	@Override
	public int getCount() {
		return ImageSource.cache.cache.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((RelativeLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(layout,container,
				false);
		imgflag = (TouchImageView) itemView.findViewById(img);
		imgflag.setImageBitmap(ImageSource.cache.get(position+""));
		((ViewPager) container).addView(itemView);
		return itemView;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((RelativeLayout) object);
	}
}