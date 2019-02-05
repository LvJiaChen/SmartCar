package com.example.qr_codescan.do_server;


import com.yolanda.nohttp.NoHttp;

import android.app.Application;
//当前应用，最先执行，优先于所有Activity
public class MyApplecation extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		NoHttp.init(this);
	}
}
