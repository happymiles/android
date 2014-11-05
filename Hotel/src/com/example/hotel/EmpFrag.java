package com.example.hotel;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EmpFrag extends Fragment {
	 EditText ed1,ed2;
	 Button b1;
	 CartDatabase cartDatabase;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.emptab, null);
		ed1 = (EditText) view.findViewById(R.id.editText1);
		ed2 = (EditText) view.findViewById(R.id.editText2);
		cartDatabase = new CartDatabase(getActivity());
		b1 = (Button) view.findViewById(R.id.button1);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//TODO Auto-generated method stub			
				SQLiteDatabase db = cartDatabase.getWritableDatabase();
				Intent i = new Intent(getActivity(),Table.class);
				i.putExtra("name",ed1.getText().toString());
				startActivity(i);
			}
		});
		return view;
	}
}
