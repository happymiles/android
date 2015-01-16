package com.example.happymiles;

import com.example.mygal.ImageSource;

import android.app.IntentService;
import android.content.Intent;

public class DownloadService extends IntentService{

	public DownloadService() {
		super("Download Service");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		ImageSource.load("http://ec2-50-17-110-77.compute-1.amazonaws.com:4000/images.json/",Login.userid+"");
	}

}
