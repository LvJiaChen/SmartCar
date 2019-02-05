package com.example.qr_codescan.fragment;
/**
 * 特价商品
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.qr_codescan.R;
import com.example.qr_codescan.do_server.FavorateServlet;
import com.example.qr_codescan.public_.Public_Attribute;

public class MyFragment3 extends Activity {
	private ListView fListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view3);
		fListView = (ListView) findViewById(R.id.favorableList);
		Public_Attribute.listView = fListView;
		new FavorateServlet().fPost(this);
	}
}
