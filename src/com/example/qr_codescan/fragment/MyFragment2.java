package com.example.qr_codescan.fragment;
/**
 * 扫描
 */
import com.example.smartcar.MipcaActivityCapture;
import com.example.qr_codescan.R;
import com.example.qr_codescan.SQLite.Insert_Scan;
import com.example.qr_codescan.do_server.Do_Scan_Selecct;
import com.example.qr_codescan.do_server.Do_post;
import com.example.qr_codescan.public_.Public_Attribute;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFragment2 extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	/**
	 * ��ʾɨ����
	 */
	private TextView mTextView;
	/**
	 * ��ʾɨ���ĵ�ͼƬ
	 */
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);

		mTextView = (TextView) findViewById(R.id.result);
		mImageView = (ImageView)findViewById(R.id.qrcode_bitmap);
		// �����ť��ת����ά��ɨ����棬�����õ���startActivityForResult��ת
		// ɨ������֮������ý���
		Button mButton = (Button) findViewById(R.id.button1);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MyFragment2.this, MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if (resultCode == -1) {
				Bundle bundle = data.getExtras();
				// ��ʾɨ�赽������
				mTextView.setText(bundle.getString("result"));
				// ��ʾ
				mImageView.setImageBitmap((Bitmap) data
						.getParcelableExtra("bitmap"));
				Public_Attribute.Scan_Result = bundle.getString("result");
				
				new Do_post().do_post(this);
			}
			break;
		}
	}

}
