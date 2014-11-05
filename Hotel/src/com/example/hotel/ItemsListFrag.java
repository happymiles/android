package com.example.hotel;

import java.util.ArrayList;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ItemsListFrag extends Fragment implements OnItemClickListener {
	ListView lv;
	ItemSelect is;
	ArrayList source, source2,source3;
	ArrayList<Data> database;
	int layout = R.layout.single_item;
	LinearLayout linearLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.itemslist, null, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		linearLayout = (LinearLayout) getActivity().findViewById(R.id.order);
		DatabaseAdapter databaseAdapter = new DatabaseAdapter(getActivity());
		source = new ArrayList();
		source = databaseAdapter.getName();

		source2 = new ArrayList();
		source2 = databaseAdapter.getPrice();

		source3 = new ArrayList();
		source3 = databaseAdapter.getId();
		
		database = new ArrayList<Data>();
		
		int i = 0;
		while (i < source.size()) {
			database.add(new Data(source3.get(i)+".",source.get(i).toString(), source2.get(i)
					.toString()));
			i++;
		}
		
		ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(),
				database,layout);
		lv = (ListView) getActivity().findViewById(R.id.listView1);
		lv.setAdapter(listViewAdapter);
		
		lv.setOnItemClickListener(this);
		is = (ItemSelect) getActivity();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String name,price,id;
		linearLayout.setVisibility(View.VISIBLE);
		name = database.get(arg2).getName().toString();
		price = database.get(arg2).getPrice().toString();
		id = database.get(arg2).getId()+".";
		is.assign(id,name, price);
	}
}