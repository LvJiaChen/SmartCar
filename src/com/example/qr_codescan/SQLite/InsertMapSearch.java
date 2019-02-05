package com.example.qr_codescan.SQLite;

import java.util.List;

import com.example.qr_codescan.public_.Commodity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class InsertMapSearch {
	private MyOpenHelper helper;
	private Cursor cursor;

	public InsertMapSearch(Context context) {
		// TODO Auto-generated constructor stub
		helper = new MyOpenHelper(context);
	}

	public void insert(List<Commodity> CList) {

		for (int i = 0; i < CList.size(); i++) {
			SQLiteDatabase db = helper.getReadableDatabase();
			Commodity commodity = CList.get(i);
			ContentValues values = new ContentValues();
			values.put("trade_name", commodity.getTrade_name());
			values.put("X", commodity.getX());
			values.put("Y", commodity.getY());
			long rowID = db.insert("commodity_map", null, values);
			if (rowID != -1) {
				Log.i("tttttttttt", "插入成功");
			} else {
				Log.i("test", "插入失败");
			}
			db.close();
		}

	}
	
	public void delete(){
		SQLiteDatabase db = helper.getReadableDatabase();
		db.execSQL("delete from commodity_map");
		db.close();
	}

}
