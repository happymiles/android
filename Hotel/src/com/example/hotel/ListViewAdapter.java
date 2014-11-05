package com.example.hotel;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter{

	Context context = null;
	ArrayList<Data> source = null;
	ViewHolder holder;
	int layout;
	MyDatabase myDatabase;
	ViewAdmin viewAdmin;
	ListViewAdapter(Context context,ArrayList<Data> source,int layout)
	{
		this.context = context;
		this.source = source;
		this.layout = layout;
		myDatabase = new MyDatabase(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return source.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return source.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	class ViewHolder
	{
		TextView tv1,tv2,tv3;
		ImageButton img;
	}
	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final int index =arg0;
		View view =null;
		view = arg1;
		
		if(arg1==null)
		{
			holder = new ViewHolder();
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(layout, null);
			holder.tv1 = (TextView) view.findViewById(R.id.textView1);
			holder.tv2 = (TextView) view.findViewById(R.id.textView2);
			if(layout == R.layout.viewitem)
			{
				holder.tv3 = (TextView) view.findViewById(R.id.textView3);
				holder.img = (ImageButton) view.findViewById(R.id.imageButton1);
				holder.img.setTag(arg0);
				holder.img.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						viewAdmin = new ViewAdmin();
						String name = source.get(index).getName();
						String price = source.get(index).getPrice();
						myDatabase.delete(name, price);
						source.remove(arg0);
						notifyDataSetChanged();
//						EditDataAdmin admin = new EditDataAdmin();
//						admin.set();
						Toast.makeText(context, "Selected"+name+" "+price,Toast.LENGTH_SHORT).show();
					}
				});
			}
			view.setTag(holder);
		}
		else
		{
			holder= (ViewHolder) view.getTag();
		}

		holder.tv1.setText(source.get(arg0).getName().toString());
		holder.tv2.setText(source.get(arg0).getPrice().toString());
		if(layout == R.layout.viewitem)
		{
			holder.tv3.setText(ViewAdmin.source3.get(arg0).toString());
		}
		return view;
	}
	
	public void insertItem(String id,String name,String price)
	{
		source.add(new Data(id, name, price));
		notifyDataSetChanged();
	}
}
