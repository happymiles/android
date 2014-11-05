package com.example.hotel;

import java.util.ArrayList;

import com.example.hotel.QuantityPickerDialog.myInterface;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CartListAdapter extends BaseAdapter implements myInterface{

	FragmentManager fragmentManager;
	Context context;
	ArrayList<CartItemData> item;
	CartDatabase cartDatabase;
	ViewCart viewCart;
	CartFrag carfrag;
	Activity activity;
	public static int initial = 0;
	
	static View buttonView;
	
	public CartListAdapter(Context context, ArrayList<CartItemData> item) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.context = context;
		this.item = item;
		// carfrag = activity;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return item.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return item.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public class Holder {
		TextView tv1, tv2, tv3;
		Button b1;
		ImageButton img;
	}

	Holder holder;

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final int index = arg0;
		View view = null;
		view = arg1;
		carfrag = new CartFrag();
		cartDatabase = new CartDatabase(context);
		viewCart = new ViewCart();
		
		if (view == null) {
			holder = new Holder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.cartitem, null);

			holder.tv1 = (TextView) view.findViewById(R.id.textView1);
			holder.tv2 = (TextView) view.findViewById(R.id.textView2);
			holder.tv3 = (TextView) view.findViewById(R.id.textView3);
			holder.b1 = (Button) view.findViewById(R.id.cart_button1);
			holder.img = (ImageButton) view.findViewById(R.id.imageButton1);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}
		initial = Integer.parseInt(item.get(arg0).getQty().toString());
		holder.tv1.setText(ViewCart.table);
		holder.tv2.setText(item.get(arg0).getName().toString());
		holder.tv3.setText(item.get(arg0).getPrice().toString());
		holder.b1.setText(initial+"");
		holder.img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = item.get(index).getName().toString();
				String price = item.get(index).getPrice().toString();
				cartDatabase.delete(ViewCart.table, name, price);
				item.remove(arg0);
				notifyDataSetChanged();
			}
		});

		holder.b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				// holder.b1.setText(initial + "");
				Log.e("Final Val before", initial+"");
//				holder.b1.setText("0");
				int val = Integer.parseInt(holder.b1.getText().toString());
				val++;
				String name = item.get(index).getName().toString();
				String price = item.get(index).getPrice().toString();
				cartDatabase.update(ViewCart.table,name, price, val+"");
				holder.tv1.setText(val+"");
				notifyDataSetChanged();
//				holder.tv1.setText(val+"");
//				Toast.makeText(context, holder.tv1.getText(), Toast.LENGTH_SHORT).show();
//				holder.b1.setText(val+"");
//				QuantityPickerDialog pickerDialog = new QuantityPickerDialog();
//				pickerDialog.show(ViewCart.fragmentManager, "Select Quantity");
			}
		});
		
		return view;
	}
	public static void set(int val) {
		// TODO Auto-generated method stub
		initial = val;
		Log.e("Set Final",initial+"");
	}
	@Override
	public void update(int val) {
		// TODO Auto-generated method stub		
	}
	
}