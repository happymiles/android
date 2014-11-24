package com.example.mygal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager; 

public class Second extends Activity {

	ViewPager pager;
	ViewPagerAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new ViewPagerAdapter(Second.this, R.layout.viewpager_item,
				R.id.flag, R.id.progressBar);
		pager.setAdapter(adapter);
		pager.setCurrentItem(getIntent().getIntExtra("Position", 1));
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		adapter.backPressed();
		super.onBackPressed();
	}
}
