package com.example.hotel;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CartFrag extends Fragment implements MyInterface{
	ListView lv;
	ArrayList<String> itemname = null;
	ArrayList<String> itemprice = null;
	ArrayList<String> qty = null;
	ArrayList<CartItemData> source = null;
	CartDatabase cartDatabase = null;
	ViewCart viewCart = null;
	MyInterface myInterface;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.cartfrag, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		setListAdap();
	}

	public void setListAdap() {
		itemname = new ArrayList<String>();
		itemprice = new ArrayList<String>();
		qty = new ArrayList<String>();

		source = new ArrayList<CartItemData>();
		cartDatabase = new CartDatabase(getActivity());
		viewCart = new ViewCart();
		myInterface = (MyInterface) getActivity();
		if (!(source.isEmpty()))
		{
			source.clear();
			itemname.clear();
			itemprice.clear();
			qty.clear();
		}
		itemname = cartDatabase.get(ViewCart.table, CartDatabase.item_name);
		itemprice = cartDatabase.get(ViewCart.table, CartDatabase.item_price);
		qty = cartDatabase.get(ViewCart.table, CartDatabase.qty);

		// source.add(new CartItemData("Item1", "Rs.100", "5"));
		int i = 0;
		while (i < itemname.size()) {
			source.add(new CartItemData(itemname.get(i), itemprice.get(i), qty
					.get(i)));
			i++;
		}
		lv = (ListView) getFragmentManager().findFragmentById(R.id.fragment1)
				.getView().findViewById(R.id.listView1);
		CartListAdapter cartListAdapter = new CartListAdapter(getActivity(),
				source);
		cartListAdapter.notifyDataSetChanged();
		lv.setAdapter(cartListAdapter);
	}
	public void xxx()
	{
		
	}

	@Override
	public void set(int val) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set() {
		// TODO Auto-generated method stub
		setListAdap();
	}
}