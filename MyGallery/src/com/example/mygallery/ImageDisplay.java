package com.example.mygallery;

import java.util.ArrayList;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

public class ImageDisplay extends ActionBarActivity {

	Bitmap[] images;
	ArrayList<Bitmap> imglist;
	Bitmap selected;
	int total;
	int pos;
	ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		
		imglist = new ArrayList<Bitmap>();
		
		Bundle b = getIntent().getExtras();
		total = b.getInt("total");
		pos = b.getInt("count");
		
		selected = b.getParcelable("selected");
		imglist = b.getParcelableArrayList("imglist");
		
		img = (ImageView) findViewById(R.id.imageView1);
		img.setImageBitmap(ImageAdapter.mThumbIds[pos]);
		img.setOnTouchListener(new OnMySwipeTouchListener(getApplicationContext()){ });
	}
	
	
	public class OnMySwipeTouchListener implements OnTouchListener {

		ImageDisplay display = new ImageDisplay();
	    private final GestureDetector gestureDetector;
	    Context context;
	    public OnMySwipeTouchListener (Context ctx){
	    	this.context = ctx;
	        gestureDetector = new GestureDetector(ctx, new GestureListener());
	    }

	    private final class GestureListener extends SimpleOnGestureListener {

	        private static final int SWIPE_THRESHOLD = 100;
	        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

	        @Override
	        public boolean onDown(MotionEvent e) {
	            return true;
	        }

	        @Override
	        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	            boolean result = false;
	            try {
	                float diffY = e2.getY() - e1.getY();
	                float diffX = e2.getX() - e1.getX();
	                if (Math.abs(diffX) > Math.abs(diffY)) {
	                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
	                        if (diffX > 0) {
	                        	
	                        	img.setImageBitmap(ImageAdapter.mThumbIds[pos-1]);
	                        	pos--;
	                            //onSwipeRight();
	                        } else {
	                        	img.setImageBitmap(ImageAdapter.mThumbIds[pos+1]);
	                        	pos++;
	                            //onSwipeLeft();
	                        }
	                    }
	                    result = true;
	                } 
	                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
	                        if (diffY > 0) {
	                            
	                        } else {
	                            
	                        }
	                    }
	                    result = true;

	            } catch (Exception exception) {
	                exception.printStackTrace();
	            }
	            return result;
	        }
	    }
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			 return gestureDetector.onTouchEvent(arg1);
		}
	}
}