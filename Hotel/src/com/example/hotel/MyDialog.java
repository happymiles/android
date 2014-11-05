package com.example.hotel;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
 
public class MyDialog extends Dialog {
 
    public MyDialog(Context context, myOnClickListener myclick) {
        super(context);
        this.myListener = myclick;
    }
 
     
    public myOnClickListener myListener;
         
        // This is my interface //
    public interface myOnClickListener {
        void onButtonClick(MyDialog dialog);
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert);
 
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new android.view.View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                //myListener.onButtonClick(this); // I am giving the click to the
                                            // interface function which we need
                                            // to implements where we call this
                                            // class
 
            }
        });
    }
 
}
