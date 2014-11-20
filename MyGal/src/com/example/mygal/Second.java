package com.example.mygal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;

public class Second extends Activity {

	ViewPager pager;
	PagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		pager = (ViewPager) findViewById(R.id.pager);

		adapter = new ViewPagerAdapter(Second.this, R.layout.viewpager_item,
				R.id.flag);
		pager.setAdapter(adapter);
		pager.setCurrentItem(getIntent().getIntExtra("Position", 1));
	}
}
