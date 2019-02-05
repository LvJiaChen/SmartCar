package com.example.qr_codescan.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
	private static final int VERSION = 2;
	public MyOpenHelper(Context context) {
		super(context, "SmartCart.db", null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
				String sql = "create table commodity(_id integer primary key autoincrement"
						+ ",trade_name" + ",price" + ",image);";
				db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if (newVersion==2) {
			String sql = "create table commodity_map(_id integer primary key autoincrement"
				+ ",trade_name" + ",X" + ",Y);";
		db.execSQL(sql);
		}
		
	}

}
