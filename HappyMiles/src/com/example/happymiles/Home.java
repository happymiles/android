package com.example.happymiles;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Home extends Activity {

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Bundle bundle = getIntent().getExtras();
		String id = bundle.getString("id");
		listView = (ListView) findViewById(R.id.listView1);
		Log.e("Activity", "Home Screen");
		String urlList[] = null, descriptions[] = null;
		try {
			JSONArray jsonArray = getData();
			urlList = new String[jsonArray.length()];
			descriptions = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				urlList[i] = jsonObject.get("thumbnail_url").toString();
				descriptions[i] = jsonObject.get("title").toString();
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
		HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(Home.this,
				urlList, descriptions);
		listView.setAdapter(homeScreenAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Home.this,Gallery.class);
				startActivity(intent);
			}
			
		});
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
}
