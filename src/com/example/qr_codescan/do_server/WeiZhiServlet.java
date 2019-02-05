package com.example.qr_codescan.do_server;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Message;
import android.util.Log;

import com.example.qr_codescan.public_.Public_Attribute;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

public class WeiZhiServlet {
	int x,y;
	RequestQueue queue = NoHttp.newRequestQueue();
	String path = "http://192.168.43.143:8080/ggg/WeiZhiServlet";
	public void do_post(String content) {
		Request<String> request = NoHttp.createStringRequest(path,
				RequestMethod.POST);
		request.add("weizhi", content);
		queue.add(66, request, new OnResponseListener<String>() {

			@Override
			public void onStart(int what) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSucceed(int what, Response<String> response) {
				Log.i("WeizhiServlet", "联网成功");
				String content = response.get();
				try {
					JSONObject jsonObject=new JSONObject(content);
					x=jsonObject.optInt("X");
					y=jsonObject.optInt("Y");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.i("WeizhiServlet",x+"");
				Log.i("WeizhiServlet111",y+"");
				Message msg=new Message();
					msg.what=1;
					msg.arg1=x;
					msg.arg2=y;
					Public_Attribute.myHandler.sendMessage(msg);

			}

			@Override
			public void onFailed(int what, String url, Object tag,
					Exception exception, int responseCode, long networkMillis) {
				Log.i("WeizhiServlet", "联网失败");

			}

			@Override
			public void onFinish(int what) {
				// TODO Auto-generated method stub

			}
		});

	}
}
