package com.example.hotel;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Order extends Activity implements ItemSelect{
	Button name,logout,cart;
	TextView tableName;
	public static String text,table;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim1,R.anim.slideout);
		setContentView(R.layout.activity_order);
		
		name = (Button) findViewById(R.id.order_button1);
		logout = (Button) findViewById(R.id.order_button2);
		cart = (Button) findViewById(R.id.order_button3);
		
		tableName = (TextView) findViewById(R.id.textView1);
		
		Intent i = getIntent();
		text = i.getStringExtra("name");
		table = i.getStringExtra("table");
		
		name.setText(text);
		tableName.setText(table);
		
		cart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), ViewCart.class);
				intent.putExtra("table",table);
				intent.putExtra("name", text);
				startActivity(intent);
			}
		});
		logout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
			}
		});
	}
	@Override
	public void assign(String id,String name, String price) {
		// TODO Auto-generated method stub
		FragmentManager fragmentManager = getFragmentManager();
		MakeOrderFrag makeOrderFrag = (MakeOrderFrag) fragmentManager.findFragmentById(R.id.fragment2);
		makeOrderFrag.fix(id,name, price);
	}
}