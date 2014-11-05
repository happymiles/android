package com.example.hotel;

import java.util.ArrayList;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewCart extends ActionBarActivity implements MyInterface{
Button b1,b2,b3;
TextView tv;
ListView lv;
public static FragmentManager fragmentManager;
public static String table,name; 
CartDatabase cartDatabase;
CartListAdapter cartListAdapter;
CartFrag cartFrag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim1,R.anim.slideout);
		setContentView(R.layout.activity_view_cart);
		
		tv = (TextView) findViewById(R.id.textView1);
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		
		Intent i = getIntent();
		table = i.getStringExtra("table");
		name = i.getStringExtra("name");
		b1.setText(name);
		tv.setText(table);
		
		cartDatabase = new CartDatabase(getApplicationContext());
		fragmentManager = getFragmentManager();
		cartFrag = new CartFrag();
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//setListAdap();
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
			}
		});
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				cartDatabase.delete(table);
			}
		});
	}
	@Override
	public void set() {
		// TODO Auto-generated method stub
		cartFrag.xxx();
	}
	@Override
	public void set(int val) {
		// TODO Auto-generated method stub
	}
}