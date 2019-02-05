package com.example.qr_codescan.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.example.qr_codescan.R;
import com.example.qr_codescan.pay.QrCodeUtil;
import com.example.qr_codescan.public_.Public_Attribute;

public class PayCodeImage extends Activity {
	private Handler handle;
	private ImageView payImpage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay);
		payImpage = (ImageView) findViewById(R.id.payImage);
		handle = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x753) {
					Bitmap mBitmap = QrCodeUtil.createQRCodeBitmap(
							(String)msg.obj, 480, 480);
					payImpage.setImageBitmap(mBitmap);
				}
			}
		};
		Public_Attribute.payHandler = handle;
	}
}
