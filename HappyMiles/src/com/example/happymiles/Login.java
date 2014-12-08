package com.example.happymiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	PopupWindow popupWindow;
	TextView textView;
	EditText ed1, ed2;
	View layout;
	Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
		b1 = (Button) findViewById(R.id.button1);

		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String email, password;
				email = ed1.getText().toString();
				password = ed2.getText().toString();
				JSONObject jsonObject;
				try {
					jsonObject = signin(email, password);

					if ((jsonObject.get("email").toString()).equals(email)) {
						String id = jsonObject.get("id").toString();
						Intent intent = new Intent(Login.this, Home.class);
						intent.putExtra("id", id);
						startActivity(intent);
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Toast.makeText(
							Login.this,
							"Incorrect email id or password. Please enter valid data",
							Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			}
		});
		textView = (TextView) findViewById(R.id.textView1);

		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText e1, e2, e3, e4;
				ImageView imageView;
				LayoutInflater inflater = (LayoutInflater) getBaseContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				layout = inflater.inflate(R.layout.registerpopup, null);
				popupWindow = new PopupWindow(layout, LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT, true);
				Button b1 = (Button) layout.findViewById(R.id.button1);

				e1 = (EditText) layout.findViewById(R.id.editText1);
				e2 = (EditText) layout.findViewById(R.id.editText2);
				e3 = (EditText) layout.findViewById(R.id.editText3);
				e4 = (EditText) layout.findViewById(R.id.editText4);
				imageView = (ImageView) layout.findViewById(R.id.imageView1);
				b1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						try {
							 JSONObject jsonObject =
							 registerUser(e1.getText().toString(),
							 e2.getText().toString(), e3.getText().toString(),
							 e4.getText().toString());
							 String id = jsonObject.get("id").toString();
							Toast.makeText(Login.this,
									"Registered Successfully "+id,
									Toast.LENGTH_SHORT).show();
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
						popupWindow.dismiss();
					}
				});
				
				
				popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 40);
				
				imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						popupWindow.dismiss();
					}
				});
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
	}

	public JSONObject signin(String email, String password)
			throws ClientProtocolException, IOException, JSONException {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		String url = "http://ec2-50-17-110-77.compute-1.amazonaws.com:4000/hm_login.json";

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("email", email));
		nameValuePairs.add(new BasicNameValuePair("password", password));

		HttpClient httpClient = new DefaultHttpClient();
		String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
		HttpGet httpGet = new HttpGet(url + "?" + paramsString);

		HttpResponse response = httpClient.execute(httpGet);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");

		JSONObject jsonObject = new JSONObject(result);
		return jsonObject;
	}


	public JSONObject registerUser(String firstname, String lastname,
			String email, String password) throws ClientProtocolException,
			IOException, JSONException {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		String url = "http://ec2-50-17-110-77.compute-1.amazonaws.com:4000/hm_users.json";

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
		
		nameValuePairs.add(new BasicNameValuePair("first_name", firstname));
		nameValuePairs.add(new BasicNameValuePair("last_name", lastname));
		nameValuePairs.add(new BasicNameValuePair("email", email));
		nameValuePairs.add(new BasicNameValuePair("password", password));

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpGet = new HttpPost(url);
		httpGet.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		HttpResponse response = httpClient.execute(httpGet);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		JSONObject jsonObject = new JSONObject(result);
		return jsonObject;
	}
}