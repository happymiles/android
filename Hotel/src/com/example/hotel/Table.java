package com.example.hotel;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Table extends Activity implements OnClickListener {

	Button t1,t2,t3,t4,name,logout;
	String text = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.anim1,R.anim.slideout);
		setContentView(R.layout.activity_table);
		name = (Button) findViewById(R.id.button1);
		logout = (Button) findViewById(R.id.button2);
		t1 = (Button) findViewById(R.id.button3);
		t2 = (Button) findViewById(R.id.button4);
		t3 = (Button) findViewById(R.id.button5);
		t4 = (Button) findViewById(R.id.button6);
		
		Intent i = getIntent();
		text = i.getStringExtra("name");
		name.setText(text);
		
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Table.this,MainActivity.class);
				startActivity(i);
			}
		});
		t1.setOnClickListener(this);
		t2.setOnClickListener(this);
		t3.setOnClickListener(this);
		t4.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(t1==arg0)
		{
			Intent i = new Intent(getApplicationContext(),Order.class);
			i.putExtra("table","1");
			i.putExtra("name",text);
			startActivity(i);
		}
		else if(t2==arg0)
		{
			Intent i = new Intent(getApplicationContext(),Order.class);
			i.putExtra("table","2");
			i.putExtra("name",text);
			startActivity(i);
		}
		else if(t3==arg0)
		{
			Intent i = new Intent(getApplicationContext(),Order.class);
			i.putExtra("table","3");
			i.putExtra("name",text);
			startActivity(i);
		}
		else if(t4==arg0)
		{
			Intent i = new Intent(getApplicationContext(),Order.class);
			i.putExtra("table","4");
			i.putExtra("name",text);
			startActivity(i);
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}
}