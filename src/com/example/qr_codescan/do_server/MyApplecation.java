package com.example.qr_codescan.do_server;


import com.yolanda.nohttp.NoHttp;

import android.app.Application;
//��ǰӦ�ã�����ִ�У�����������Activity
public class MyApplecation extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		NoHttp.init(this);
	}
}
