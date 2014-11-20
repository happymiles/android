package com.example.mygal;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;

public class ImageLoad {
	Context context;
	String url;
	String group_id;
	Class secondActivityClass;
	ImageAdapter adapter;
	GridView gridView;
	ProgressBar progressBar;
	public ImageLoad(Context context,String url,String group_id,GridView gridView,ProgressBar progressBar,Class secondActivityClass) {
		this.context = context;
		this.url = url;
		this.group_id = group_id;
		this.gridView = gridView;
		this.secondActivityClass = secondActivityClass;
		this.progressBar = progressBar;
		new Download().execute();
	}

	class Download extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			adapter = new ImageAdapter(context,url,group_id);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			gridView.setVisibility(View.VISIBLE);
			gridView.setAdapter(adapter);
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					Intent i = new Intent(context,secondActivityClass);
					i.putExtra("Position", arg2);
					context.startActivity(i);
				}
			});
			progressBar.setVisibility(View.GONE);
		}
	}
}
