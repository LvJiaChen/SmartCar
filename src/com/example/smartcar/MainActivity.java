package com.example.smartcar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.qr_codescan.R;
import com.example.qr_codescan.SQLite.Insert_Scan;
import com.example.qr_codescan.fragment.MyFragment1;
import com.example.qr_codescan.fragment.MyFragment2;
import com.example.qr_codescan.fragment.MyFragment3;
import com.example.qr_codescan.fragment.MyFragment4;
import com.example.qr_codescan.public_.Public_Attribute;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	private Button button1, button2, button3, button4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MyFragment1.class);
				startActivity(intent);
			}
		});

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MyFragment2.class);
				startActivity(intent);

			}
		});

		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MyFragment3.class);
				startActivity(intent);

			}
		});

		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MyFragment4.class);
				startActivity(intent);

			}
		});
	}

	private void init() {
		button1 = (Button) findViewById(R.id.button_dh);
		button2 = (Button) findViewById(R.id.button_spjgsm);
		button3 = (Button) findViewById(R.id.button_kkcxsp);
		button4 = (Button) findViewById(R.id.button_ygmsp);

	}

	@Override
	protected void onDestroy() {
		new Insert_Scan(this).delete();
		super.onDestroy();
	}
}
