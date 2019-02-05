package com.example.qr_codescan.public_;

import java.util.List;

import com.example.qr_codescan.fragment.MyFragment4;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.widget.ListView;

public class Public_Attribute {
	public static String Scan_Result;
	public static String trade_name;
	public static double price;
	public static String image;
	public static String imFile;
	public static List<FavoratePojo> fList;
	public static ListView listView;
	public static Handler myHandler;
	public static Handler payHandler;
	public static Handler shelfHandler;
}
