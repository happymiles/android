package com.example.hotel;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {

	ArrayList<String> source = null;
	ArrayList<String> source2 = null;
	ArrayList<String> source3 = null;
	Context context = null;
	MyDatabase database;
	SQLiteDatabase sqLiteDatabase;

	public DatabaseAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		
		database = new MyDatabase(context);
		
	}

	public ArrayList<String> getName() {
		source = new ArrayList<String>();
		sqLiteDatabase = database.getReadableDatabase();
		String cols[] = { MyDatabase.item_name, MyDatabase.item_price };
		Cursor cursor = sqLiteDatabase.query(MyDatabase.tbname, cols, null,
				null, null, null, null);

		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor
					.getColumnIndex(MyDatabase.item_name));
			source.add(name);
		}
		sqLiteDatabase.close();
		return source;
	}

	public ArrayList<String> getPrice() {
		source2 = new ArrayList<String>();
		sqLiteDatabase = database.getReadableDatabase();
		String cols[] = { MyDatabase.item_name, MyDatabase.item_price };
		Cursor cursor = sqLiteDatabase.query(MyDatabase.tbname, cols, null,
				null, null, null, null);

		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor
					.getColumnIndex(MyDatabase.item_price));
			source2.add(name);
		}
		sqLiteDatabase.close();
		return source2;
	}
	
	public ArrayList<String> getId() {
		source3 = new ArrayList<String>();
		sqLiteDatabase = database.getReadableDatabase();
		String cols[] = { MyDatabase.item_id};
		Cursor cursor = sqLiteDatabase.query(MyDatabase.tbname, cols, null,
				null, null, null, null);

		while (cursor.moveToNext()) {
			String id = cursor.getString(cursor
					.getColumnIndex(MyDatabase.item_id));
			source3.add(id);
		}
		sqLiteDatabase.close();
		return source3;
	}
}