package com.example.hotel;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity implements TabListener {

	ActionBar actionBar = null;
	ViewPager pager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim1,R.anim.slideout);
		setContentView(R.layout.activity_main);

		pager = (ViewPager) findViewById(R.id.pager);
		MyPagerAdapter myPagerAdapter = new MyPagerAdapter(
				getSupportFragmentManager());
		pager.setAdapter(myPagerAdapter);

		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab adminTab = actionBar.newTab();
		adminTab.setText("Admin");
		adminTab.setTabListener(this);
		actionBar.addTab(adminTab);

		ActionBar.Tab empTab = actionBar.newTab();
		empTab.setText("Employee");
		empTab.setTabListener(this);
		actionBar.addTab(empTab);

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if (arg0 == 0)
					actionBar.setSelectedNavigationItem(0);
				else
					actionBar.setSelectedNavigationItem(1);
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
		if (tab.getPosition() == 0)
			pager.setCurrentItem(0);
		else
			pager.setCurrentItem(1);
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("Hotel");
		builder.setMessage("Do you want to exit?");
		builder.setPositiveButton("Ok",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub

	             Intent intent = new Intent(Intent.ACTION_MAIN);
	             intent.addCategory(Intent.CATEGORY_HOME);
	             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	             startActivity(intent);
			}
		});
		builder.setNegativeButton("Cancel",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
			}
		});
		builder.show();
	}
}

class MyPagerAdapter extends FragmentPagerAdapter {

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment frag = null;
		if (arg0 == 0)
			frag = new AdminFrag();
		else
			frag = new EmpFrag();
		return frag;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}