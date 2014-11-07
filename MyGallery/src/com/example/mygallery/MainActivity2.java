package com.example.mygallery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
 
public class MainActivity2 extends Activity {
    ViewPager viewPager;
    PagerAdapter adapter;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
 
        Intent in = getIntent();
        int pos = in.getIntExtra("count", 1);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(MainActivity2.this,ImageAdapter.filepath,ImageAdapter.mThumbIds);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(pos);
    }
}