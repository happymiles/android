package com.example.hotel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertAdmin extends Fragment {
	EditText ed1,ed2;
	Button b1,b2;
	MyDatabase myDatabase;
	ViewAdmin viewAdmin;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.insertadmin, null);
		return view;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		viewAdmin = new ViewAdmin();
		ed1 = (EditText) getActivity().findViewById(R.id.editText1);
		ed2 = (EditText) getActivity().findViewById(R.id.editText2);
		
		b1 = (Button) getActivity().findViewById(R.id.button1);
		b2 = (Button) getActivity().findViewById(R.id.button2);
		
		b1.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myDatabase = new MyDatabase(getActivity());
				myDatabase.insertItems(ed1.getText().toString(),"Rs."+ed2.getText());
				//viewAdmin.insert(ed1.getText().toString(), ed2.getText().toString());
				Toast.makeText(getActivity(), "Inserted successfully",Toast.LENGTH_SHORT).show();
				ed1.setText("");
				ed2.setText("");
			}
		});
		b2.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				ed1.setText("");
				ed2.setText("");
			}
		});
	}
}
