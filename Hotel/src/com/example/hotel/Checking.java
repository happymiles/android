package com.example.hotel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hotel.MyDialog.myOnClickListener;

public class Checking extends Activity {
	 public myOnClickListener myListener;
	 Button b1;
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main1);
	 
	        // create new onclicklistener interface //
	        MyDialog mydialog = new MyDialog(Checking.this, myListener);
	        mydialog.show();
	
	 myListener = new myOnClickListener() {
         @Override
         public void onButtonClick(MyDialog dialog) {
             System.out.println("I am clicking the button in the dialog");
             Toast.makeText(getApplicationContext(),
                     "I am clicking the button in the dialog",
                     Toast.LENGTH_LONG).show();
             dialog.dismiss();
             
            
         }
     };
	 
	    }
}
