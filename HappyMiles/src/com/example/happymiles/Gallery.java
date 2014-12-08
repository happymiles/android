package com.example.happymiles;

import com.example.mygal.ImageLoad;
import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ProgressBar;

public class Gallery extends Activity {
	GridView gridView;
	ProgressBar progressBar;
	String url = "http://ec2-50-17-110-77.compute-1.amazonaws.com:4000/images.json/";
	String group_id = "1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		gridView = (GridView) findViewById(R.id.gridView1);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		new ImageLoad(Gallery.this, url, group_id, gridView, progressBar,
				ImageScreen.class);
	}
}
