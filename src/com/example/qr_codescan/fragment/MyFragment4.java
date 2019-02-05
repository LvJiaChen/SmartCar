package com.example.qr_codescan.fragment;
/**
 * 已购买商品
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qr_codescan.R;
import com.example.qr_codescan.SQLite.Insert_Scan;
import com.example.qr_codescan.do_server.PayServlet;

public class MyFragment4 extends Activity {
	private ListView scanList;
	private TextView total;
	private Button fukuan;
	private double p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view4);
		scanList = (ListView) findViewById(R.id.scan_list);
		total = (TextView) findViewById(R.id.total);
		fukuan = (Button) findViewById(R.id.fukaun);
		fukuan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new PayServlet().pay(p+"");
				Intent intent = new Intent(MyFragment4.this, PayCodeImage.class);
				startActivity(intent);
			}
		});
		Cursor cursor = new Insert_Scan(this).select();
		CursorAdapter cursorAdapter = new CursorAdapter(this, cursor) {

			@Override
			public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
				View view = LayoutInflater.from(arg0).inflate(
						R.layout.scan_item, null);

				return view;
			}

			@Override
			public void bindView(View arg0, final Context arg1,
					final Cursor arg2) {
				ImageView tvImagePath = (ImageView) arg0
						.findViewById(R.id.imageView1);
				TextView tvTrade_name = (TextView) arg0
						.findViewById(R.id.textView1);
				TextView tvPrice = (TextView) arg0.findViewById(R.id.textView2);
				ImageButton button = (ImageButton) arg0
						.findViewById(R.id.imageButton1);

				String trade_name = arg2.getString(arg2
						.getColumnIndex("trade_name"));
				String price = arg2.getString(arg2.getColumnIndex("price"));
				String imagePath = arg2.getString(arg2.getColumnIndex("image"));
				Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
				tvImagePath.setImageBitmap(bitmap);
				tvTrade_name.setText(trade_name);
				tvPrice.setText(price);
				button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// new Insert_Scan(arg1).deleteCursor(arg2);

					}
				});
			}
		};
		scanList.setAdapter(cursorAdapter);

		p = new Insert_Scan(this).selectPrice();
		total.setText("" + p);

	}

}
