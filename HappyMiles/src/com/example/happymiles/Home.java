package com.example.happymiles;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mygal.ImageSource;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

public class Home extends Activity {

	ListView listView;
	ProgressBar bar;
	Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		b1 = (Button) findViewById(R.id.button1);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Home.this, Login.class);
				startActivity(intent);
			}
		});
		ImageSource.images.clear();		
		ImageSource.cache.group_thumbnail.clear();
		Log.e("Check", "Started");
		new Download().execute();
	}

	public JSONArray getData() throws ClientProtocolException, IOException,
			JSONException {
		JSONArray jsonArray;

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		String url = "http://ec2-50-17-110-77.compute-1.amazonaws.com:4000/hm_resources.json";

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		HttpResponse response = httpClient.execute(httpGet);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		jsonArray = new JSONArray(result);
		return jsonArray;
	}
	
	public void copy(String id)
	{
		int i;
		int count=0;
		Log.e("BeforeCopy-Size",ImageSource.images.size()+"");
		ImageSource.images.clear();
		ImageSource.currentTitle.clear();
		ImageSource.currentDescriptions.clear();
		Log.e("Size",ImageSource.cache.getCacheSize()+"");
		for(i = 0;i<ImageSource.cache.getCacheSize();i++)
		{
			if((ImageSource.img_group_id.get(i)).toString().equals(id))
			{
				Log.e("Count", count+"");
				ImageSource.cache.putGroupImage(count+"",ImageSource.cache.get(i+""));
				ImageSource.images.add(ImageSource.imageslist[i]);
				ImageSource.currentTitle.add(ImageSource.title.get(i));
				ImageSource.currentDescriptions.add(ImageSource.descriptions.get(i));
				count++;
			}
		}
		Log.e("AfterCopy-Size", ImageSource.images.size()+"");
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}
	class Download extends AsyncTask<Void, Void, Void> {
		ArrayList<String> url,desc,img_group_id;
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			bar = (ProgressBar) findViewById(R.id.progressBar1);
			listView = (ListView) findViewById(R.id.listView1);
			Log.e("Activity", "Home Screen");

			try {
				JSONArray jsonArray = getData();
				url = new ArrayList<String>();
				desc = new ArrayList<String>();
				img_group_id = new ArrayList<String>();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					if (jsonObject.get("hm_user_id").toString()
							.equals(Login.userid + "")) {
						url.add(jsonObject.get("thumbnail_url").toString());
						desc.add(jsonObject.get("title").toString());
						img_group_id.add(jsonObject.getString("image_group_id"));
					}
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			listView.setVisibility(View.VISIBLE);
			HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(
					Home.this, url, desc);
			listView.setAdapter(homeScreenAdapter);

			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Log.e("Position", arg2+"");
					Log.e("GroupId", img_group_id.get(arg2)+"");
					Log.e("Size", ImageSource.images.size()+"");
					ImageSource.images.clear();
					ImageSource.cache.image.clear();
					Log.e("SizeCleared", ImageSource.images.size()+"");
					copy(img_group_id.get(arg2));
					
					Intent intent = new Intent(Home.this, Gallery.class);
					intent.putExtra("group_id", img_group_id.get(arg2));
					startActivity(intent);
				}
			});
			bar.setVisibility(View.GONE);
		}
	}
}
