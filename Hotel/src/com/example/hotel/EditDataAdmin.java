package com.example.hotel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class EditDataAdmin extends ActionBarActivity implements TabListener{

	ActionBar action = null;
	ViewPager pager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim1,R.anim.slideout);
		setContentView(R.layout.activity_edit_data_admin);
		
		pager = (ViewPager) findViewById(R.id.edit_pager);
		EditPagerAdapter editPagerAdapter = new EditPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(editPagerAdapter);
		
		action = getActionBar();
		action.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab tab1 = action.newTab();
		tab1.setText("View");
		tab1.setTabListener(this);
		action.addTab(tab1);
		
		ActionBar.Tab tab2 = action.newTab();
		tab2.setText("Insert");
		tab2.setTabListener(this);
		action.addTab(tab2);
		
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if(arg0==0)
					action.setSelectedNavigationItem(0);
				else if(arg0==1)
					action.setSelectedNavigationItem(1);
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		if(tab.getPosition()==0)
			pager.setCurrentItem(0);
		else if(tab.getPosition()==1)
			pager.setCurrentItem(1);
	}
	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}
	public void set()
	{
//		FragmentManager fragmentManager = getSupportFragmentManager();
//		ViewAdmin viewAdmin = (ViewAdmin) fragmentManager.findFragmentById(R.id.fragment1);
		ViewAdmin viewAdmin = new ViewAdmin();
		viewAdmin.setListView();
	}
}
class EditPagerAdapter extends FragmentPagerAdapter
{

	public EditPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment frag = null;
		if(arg0==0)
			frag = new ViewAdmin();
		else if(arg0==1)
			frag = new InsertAdmin();
		return frag;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
}