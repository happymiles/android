package com.example.hotel;


import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

public class QuantityPickerDialog extends DialogFragment implements
		OnClickListener {
	public NumberPicker numberPicker;
	Button b1, b2, my;
	static int finalVal = 0;
	CartListAdapter cartListAdapter;
	myInterface interface1;
	public interface myInterface
	{
		public void update(int val);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.qpicker, null);
		numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker1);
		numberPicker.setMinValue(1);
		numberPicker.setMaxValue(99);
				
		b1 = (Button) view.findViewById(R.id.button1);
		b2 = (Button) view.findViewById(R.id.button2);

		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		return view;
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.button1) {
			finalVal = numberPicker.getValue();
			interface1.update(finalVal);
		}
		dismiss();
	}

	public void setValue(myInterface val)
	{
		this.interface1=val;
	}
}
