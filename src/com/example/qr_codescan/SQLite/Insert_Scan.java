package com.example.qr_codescan.SQLite;

import java.io.File;

import com.example.qr_codescan.public_.Public_Attribute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Insert_Scan {
	private MyOpenHelper helper;
	private Cursor cursor;
	private double totalPrice;

	public Insert_Scan(Context context) {
		helper = new MyOpenHelper(context);
	}

	// 插入数据
	public void insert() {
		SQLiteDatabase db = helper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("trade_name", Public_Attribute.trade_name);
		values.put("price", Public_Attribute.price);
		values.put("image", Public_Attribute.imFile);
		long rowID = db.insert("commodity", null, values);
		if (rowID != -1) {
			Log.i("tttttttttt", "插入成功");
		} else {
			Log.i("test", "插入失败");
		}
		db.close();
	}

	// 查找数据
	public Cursor select() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("commodity", null, null, null, null, null,
				null);
		return cursor;
	}

	// 删除数据
	public void delete() {
		SQLiteDatabase db = helper.getReadableDatabase();
		db.execSQL("delete from commodity");
		db.close();

	}

	// 消费总额
	public double selectPrice() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("commodity", new String[] { "price" }, null,
				null, null, null, null);
		totalPrice = 0;
		while (cursor.moveToNext()) {

			totalPrice += Double.parseDouble(cursor.getString(cursor
					.getColumnIndex("price")));
		}
		db.close();
		return totalPrice;

	}
  
	//删除当前数据
	public void deleteCursor(Cursor cursor){
		SQLiteDatabase db=helper.getReadableDatabase();
		db.delete("commodity", "_id=?", new String[]{cursor.getString(cursor.getColumnIndex("_id"))});
		db.close();
	}
}
