package com.example.hotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper {

	public static final int version = 1;
	public static final String dbname = "testdb";
	public static final String tbname = "emp";
	public static final String item_id = "id";
	public static final String item_price = "price";
	public static final String item_name = "name";
	static final String CREATE_DB_TABLE = "CREATE TABLE " + MyDatabase.tbname
			+ "(" + MyDatabase.item_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ MyDatabase.item_name + " VARCHAR(25)," + MyDatabase.item_price
			+ " VARCHAR(15));";
	Context context;

	public MyDatabase(Context context) {
		super(context, dbname, null, version);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			db.execSQL(MyDatabase.CREATE_DB_TABLE);
			Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + MyDatabase.tbname);
		onCreate(db);
	}

	public void insertItems(String name,String price) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(MyDatabase.item_name, name);
		contentValues.put(MyDatabase.item_price, price);

		db.insert(MyDatabase.tbname, null,contentValues);
		
		db.close();
	}
	
	public void delete(String name,String price)
	{
		SQLiteDatabase db = getWritableDatabase();
		String where = item_name+"=? and "+item_price+"=?";
		String args[]={name,price};
		db.delete(tbname, where, args);
		Toast.makeText(context, "Deleted",Toast.LENGTH_SHORT).show();
	}
}
