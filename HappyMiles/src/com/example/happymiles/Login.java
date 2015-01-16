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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	PopupWindow popupWindow;
	TextView textView;
	EditText ed1, ed2;
	View layout;
	Button b1;
	ProgressBar bar;
	static int userid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
		b1 = (Button) findViewById(R.id.button1);
		bar = (ProgressBar) findViewById(R.id.progressBar1);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				bar.setVisibility(View.VISIBLE);
				String email, password;
				email = ed1.getText().toString();
				password = ed2.getText().toString();
				try {
					login(email, password);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		textView = (TextView) findViewById(R.id.textView1);

		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText e1, e2, e3, e4, e5;
				ImageView imageView;
				final ProgressBar bar2;
				LayoutInflater inflater = (LayoutInflater) getBaseContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				layout = inflater.inflate(R.layout.registerpopup, null);
				popupWindow = new PopupWindow(layout, LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT, true);
				Button b1 = (Button) layout.findViewById(R.id.button1);
				bar2 = (ProgressBar) layout.findViewById(R.id.progressBar2);
				e1 = (EditText) layout.findViewById(R.id.editText1);
				e2 = (EditText) layout.findViewById(R.id.editText2);
				e3 = (EditText) layout.findViewById(R.id.editText3);
				e4 = (EditText) layout.findViewById(R.id.editText4);
				e5 = (EditText) layout.findViewById(R.id.editText5);

				imageView = (ImageView) layout.findViewById(R.id.imageView1);
				b1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						try {
							String firstname = e1
									.getText().toString();
							String lastname = e2
									.getText().toString();
							String email = e3
									.getText().toString();
							String pass = (e4.getText().toString());
							String passconfirm = (e5.getText().toString());
							Log.e("Password Check", pass+" "+passconfirm);
							if (pass.equals(passconfirm)) {
								
								bar2.setVisibility(View.VISIBLE);
								Log.e("Details", firstname+" "+lastname+" "+email+" "+pass+"");
								JSONObject jsonObject = registerUser(firstname,lastname,email,pass);
								Log.e("Status", "Success");
								String id = jsonObject.get("id").toString();
								Log.e("ID", id+"");
								Toast.makeText(Login.this,
										"Registered Successfully " + id,
										Toast.LENGTH_SHORT).show();
								//popupWindow.dismiss();
								login(e3.getText().toString(), e4.getText().toString());
							} else {
									e4.setText("");
									e5.setText("");
									Toast.makeText(Login.this, "Please confirm your password correctly", Toast.LENGTH_SHORT).show();
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
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
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
	
	public void login(String email,String password)throws Exception
	{
		JSONObject jsonObject;
		try {
			jsonObject = signin(email, password);

			if ((jsonObject.get("email").toString()).equals(email)) {
				String id = jsonObject.get("id").toString();
				userid = Integer.parseInt(id);

				Intent intent = new Intent(Login.this, Home.class);
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

	class Download extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			// Log.e("Size",ImageSource.cache.cache.size()+"");
			// ImageSource.load("http://ec2-50-17-110-77.compute-1.amazonaws.com:4000/images.json/","1");
			return null;
		}
	}
}