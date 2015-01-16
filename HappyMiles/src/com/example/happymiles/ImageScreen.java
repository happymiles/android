package com.example.happymiles;

import com.example.mygal.ViewPagerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class ImageScreen extends Activity {

	ViewPager pager;
	ViewPagerAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_screen);
		Log.e("ImageScreen", "Started");
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new ViewPagerAdapter(ImageScreen.this, R.layout.viewpager_item,
				R.id.flag, R.id.progressBar);
		pager.setAdapter(adapter);
		pager.setCurrentItem(getIntent().getIntExtra("Position", 1));
	}
	
	@Override
	public void onBackPressed() {
		adapter.backPressed();
		super.onBackPressed();
		System.gc();
	}
}