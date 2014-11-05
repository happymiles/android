package com.example.hotel;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class ViewAdmin extends Fragment {

	ListView lv;
	ArrayList<Data> database;
	ArrayList source, source2;
	static ArrayList source3;
	int layout = R.layout.viewitem;
	ListViewAdapter listViewAdapter = null;
	MyDatabase myDatabase;
	int i = 0;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.viewadmin, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setListView();
	}
	public void setListView() {
		lv = (ListView) getActivity().findViewById(R.id.listView1);
		DatabaseAdapter databaseAdapter = new DatabaseAdapter(getActivity());
		myDatabase = new MyDatabase(getActivity());
		source = new ArrayList();
		source = databaseAdapter.getName();

		source2 = new ArrayList();
		source2 = databaseAdapter.getPrice();

		source3 = new ArrayList();
		// source3 = databaseAdapter.getId();
		this.source3 = source3;
		database = new ArrayList<Data>();

		
		while (i < source.size()) {
			source3.add((i + 1) + ".");
			database.add(new Data(source3.get(i) + ".", source.get(i)
					.toString(), source2.get(i).toString()));
			i++;
		}
		listViewAdapter = new ListViewAdapter(getActivity(), database, layout);
		lv.setAdapter(listViewAdapter);
	}
	
	public void insert(String name,String price)
	{
		listViewAdapter.insertItem(i+"", name, price);
	}
}