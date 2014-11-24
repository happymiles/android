package com.example.mygal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class ViewPagerAdapter extends PagerAdapter {
	Context context;
	LayoutInflater inflater;
	Bitmap[] imgs;
	String filepath[];
	int layout;
	int img;
	int progress;
	TouchImageView imgflag;
	ProgressBar bar;
	Download download;
	public ViewPagerAdapter(Context context, int layout, int img, int progress) {
		this.context = context;
		this.img = img;
		this.layout = layout;
		this.progress = progress;
	}

	@Override
	public int getCount() {
		return ImageSource.cache.cache.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((RelativeLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(layout, container, false);
		imgflag = (TouchImageView) itemView.findViewById(img);
		bar = (ProgressBar) itemView.findViewById(progress);
		if (ImageSource.cache.getImage(position + "") != null) {
			imgflag.setImageBitmap(ImageSource.cache.getImage(position + ""));
			bar.setVisibility(View.INVISIBLE);
			imgflag.setVisibility(View.VISIBLE);
		} else {
			download = new Download(position, imgflag, bar);
			download.execute();
		}

		((ViewPager) container).addView(itemView);
		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((RelativeLayout) object);
	}

	class Download extends AsyncTask<Void, Void, Void> {
		int position;
		ImageView imageView;
		ProgressBar progressBar;

		public Download(int position, ImageView imageView,
				ProgressBar progressBar) {
			this.position = position;
			this.imageView = imageView;
			this.progressBar = progressBar;
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(new URL(
						ImageSource.imageslist[position]).openStream());

				if (bitmap.getWidth() > 2000 || bitmap.getHeight() > 2000)
					bitmap = Bitmap.createScaledBitmap(bitmap,
							(int) Math.round(bitmap.getWidth() * 0.5),
							(int) Math.round(bitmap.getHeight() * 0.5), true);
				ImageSource.cache.putImage(position + "", bitmap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			imageView.setImageBitmap(ImageSource.cache.getImage(position + ""));
			progressBar.setVisibility(View.INVISIBLE);
			imageView.setVisibility(View.VISIBLE);
		}
	}
	
	public void backPressed()
	{
		download.cancel(true);
	}
}