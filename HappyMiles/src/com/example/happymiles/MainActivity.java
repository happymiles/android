package com.example.happymiles;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import com.crittercism.app.Crittercism;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Crittercism.initialize(getApplicationContext(), "54aa8a0f51de5e9f042ec7e0");
		setContentView(R.layout.activity_main);
		check();
	}

	public void check() {
		if (haveNetworkConnection()) {
			startHappyMiles();
		} else {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

			alertDialog.setPositiveButton("Try Again", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub					
					check();
				}
			});
			alertDialog.setNegativeButton("Exit", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
				}
			});
			alertDialog.setTitle("Alert");
			alertDialog.setIcon(R.drawable.happymilesicon);
			alertDialog.setCancelable(false);
			alertDialog.setMessage("No internet connection!!!");
			AlertDialog dialog = alertDialog.create();
			dialog.show();
		}
	}

	public void startHappyMiles() {
		Intent i = new Intent(getApplicationContext(), DownloadService.class);
		startService(i);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				startActivity(new Intent(MainActivity.this, Login.class));
				finish();
			}
		}, 5000);
	}

	public boolean haveNetworkConnection() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;
		
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}
}