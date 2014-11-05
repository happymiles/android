package com.example.hotel;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MakeOrderFrag extends Fragment {
	TextView tv1, tv2;
	ImageButton img1,img2;
	Button b1;
	EditText ed1;
	CartDatabase cartDatabase;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.orderitems, null, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		cartDatabase = new CartDatabase(getActivity());
		tv1 = (TextView) getActivity().findViewById(R.id.textView1_order);
		tv2 = (TextView) getActivity().findViewById(R.id.textView2_order);
		
		img1 = (ImageButton) getActivity().findViewById(R.id.imageButton1);
		img2 = (ImageButton) getActivity().findViewById(R.id.imageButton2);
		
		ed1 = (EditText) getActivity().findViewById(R.id.editText1);
		b1 = (Button) getActivity().findViewById(R.id.button1);
		
		img1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int no = Integer.parseInt(ed1.getText().toString());
				if(no<99)
					no++;
				ed1.setText(no+"");
			}
		});
		img2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int no = Integer.parseInt(ed1.getText().toString());
				if(no>1)
					no--;
				ed1.setText(no+"");
			}
		});
		b1.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String item_name = tv1.getText().toString();
				String item_price = tv2.getText().toString();
				String qty = ed1.getText().toString();
				String table = Order.table;
				if(cartDatabase.getDuplicate(table, item_name, item_price))
				{
					cartDatabase.update(table, item_name, item_price, qty);
				}
				else
					cartDatabase.insertItem(table, item_name, item_price,qty);		
			}
		});
	}

	public void fix(String id,String name, String price) {
		tv1.setText(name);
		tv2.setText(price);
	}
}
