package com.example.mygal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

public class ImageSource {
	static MemoryCache cache = new MemoryCache();
	static String urlList[];

	public static void load(String url, String group_id) {
		Bitmap bitmap;

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		try {
			JSONArray jsonObject = getArr(url);
			urlList = new String[jsonObject.length()];

			for (int i = 0; i < 3; i++) {
				JSONObject object = jsonObject.getJSONObject(i);
				if (object.getString("image_group_id").equals(group_id)) {
					urlList[i] = object.getString("url");

					bitmap = BitmapFactory.decodeStream(new URL(urlList[i])
							.openStream());
					int width = bitmap.getWidth();
					int height = bitmap.getHeight();
					if (width > 2000 || height > 2000)
						bitmap = Bitmap.createScaledBitmap(bitmap,
								(int) Math.round(bitmap.getWidth() * 0.5),
								(int) Math.round(bitmap.getHeight() * 0.5),
								true);
					cache.put(i + "", bitmap);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JSONArray getArr(String url) {
		JSONArray jsonArray = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);

			HttpResponse httpResponse = httpClient.execute(httpGet);
			String result = EntityUtils.toString(httpResponse.getEntity());

			jsonArray = new JSONArray(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}