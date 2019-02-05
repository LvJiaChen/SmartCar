package com.example.qr_codescan.do_server;

import android.os.Message;
import android.util.Log;

import com.example.qr_codescan.public_.Public_Attribute;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

public class PayServlet {
	RequestQueue queue = NoHttp.newRequestQueue();
	String path = "http://192.168.43.143:8080/ggg/Pay_Servlet";

	public void pay(String zPrice){
		Request<String> request = NoHttp.createStringRequest(path,
				RequestMethod.POST);
		request.add("zPrice", zPrice);
		queue.add(99, request, new OnResponseListener<String>() {

			@Override
			public void onStart(int what) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSucceed(int what, Response<String> response) {
				String codeurl=response.get();
				Log.i("codeurl", codeurl);
				Message msg=new Message();
				msg.what=0x753;
				msg.obj=codeurl;
				Public_Attribute.payHandler.sendMessage(msg);
			}

			@Override
			public void onFailed(int what, String url, Object tag,
					Exception exception, int responseCode, long networkMillis) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFinish(int what) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
