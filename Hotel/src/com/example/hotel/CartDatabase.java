package com.example.hotel;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class CartDatabase extends SQLiteOpenHelper {

	ArrayList<String> item;
	public static final int version = 1;
	public static final String dbname = "cartdb";
	public static final String tbname = "carttb";
	public static final String tbno = "Table_No";
	public static final String id = "id";
	public static final String item_name = "name";
	public static final String item_price = "price";
	public static final String qty = "quantity";

	public static final String CREATE_TB = "CREATE TABLE carttb(" + id
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + tbno + " INTEGER,"
			+ item_name + " VARCHAR(25),"+qty+" VARCHAR(10)," + item_price + " VARCHAR(25));";

	Context context;

	public CartDatabase(Context context) {
		super(context, dbname, null, version);
		// TODO Auto-generated constructor stu
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CartDatabase.CREATE_TB);
		Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		try {
			db.execSQL("DROP TABLE IF EXISTS " + CartDatabase.tbname);
			onCreate(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertItem(String tableNo,String itemName,String itemprice,String qty) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(CartDatabase.tbno, tableNo);
		values.put(CartDatabase.item_name,itemName);
		values.put(CartDatabase.item_price,itemprice);
		values.put(CartDatabase.qty,qty);
		db.insert(CartDatabase.tbname,null,values);
		db.close();
		Toast.makeText(context, "Ordered", Toast.LENGTH_SHORT).show();
	}
	
	public ArrayList<String> get(String tbno,String col)
	{
		item = new ArrayList<String>();
		SQLiteDatabase db = getReadableDatabase();
		String cols[] = {CartDatabase.tbno,CartDatabase.item_name,CartDatabase.item_price,CartDatabase.qty};
		String sel = CartDatabase.tbno+"=?";
		String args[]={tbno};
		Cursor cursor = db.query(CartDatabase.tbname,cols, sel, args,null,null,null);
		
		while(cursor.moveToNext())
		{
			String name = cursor.getString(cursor.getColumnIndex(col));
			item.add(name);
		}
		db.close();
		return item;
	}
	
	public Boolean getDuplicate(String tbno,String item,String price)
	{
		int count=0;
		SQLiteDatabase db = getReadableDatabase();
		String cols[] = {CartDatabase.tbno,CartDatabase.item_name,CartDatabase.item_price};
		String sel = CartDatabase.tbno+"=? and "+CartDatabase.item_name+"=? and "+CartDatabase.item_price+"=?";
		String args[]={tbno,item,price};
		Cursor cursor = db.query(CartDatabase.tbname,cols, sel, args,null,null,null);
		
		while(cursor.moveToNext())
		{
			count++;
		}
		db.close();
		if(count==0)
			return false;
		else
			return true;
	}
	
	public void delete(String tbno)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String where = CartDatabase.tbno+"=?";
		String args[]={tbno};
		db.delete(CartDatabase.tbname, where, args);
		Toast.makeText(context, "Order cleared successfully",Toast.LENGTH_SHORT).show();
		db.close();
	}
	
	public void delete(String tbno,String itemname,String itemprice)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String where = CartDatabase.tbno+"=? and "+CartDatabase.item_name+"=? and "+CartDatabase.item_price+"=?";
		String args[]={tbno,itemname,itemprice};
		db.delete(CartDatabase.tbname, where, args);
		Toast.makeText(context, "Item cancelled",Toast.LENGTH_SHORT).show();
		db.close();
	}
	
	public void update(String tbno,String itemname,String itemprice,String qty)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String where = CartDatabase.tbno+"=? and "+CartDatabase.item_name+"=? and "+CartDatabase.item_price+"=?";
		String args[]={tbno,itemname,itemprice};
		ContentValues values = new ContentValues();
		values.put(CartDatabase.qty,qty);
		db.update(CartDatabase.tbname, values, where, args);
		Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
		db.close();
	}
}