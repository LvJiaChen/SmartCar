package com.example.qr_codescan.do_server;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.example.qr_codescan.public_.Public_Attribute;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

public class ShelfServlet {
	RequestQueue queue = NoHttp.newRequestQueue();
	String path = "http://192.168.43.143:8080/ggg/ShelfServlet";
	List<String> fList = new ArrayList<String>();

	public void do_post(final Context context,String shelf) {
		Request<String> request = NoHttp.createStringRequest(path,
				RequestMethod.POST);
		request.add("shelf", shelf);
		queue.add(44, request, new OnResponseListener<String>() {

			@Override
			public void onStart(int what) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSucceed(int what, Response<String> response) {
				Log.i("succe","成功联网");
				String content = response.get();
				try {
					JSONArray jArray = new JSONArray(content);
					for(int i = 0; i < jArray.length(); i++){
						JSONObject jsonObject = jArray.getJSONObject(i);
						fList.add(jsonObject.optString("trade_name"+i));
					}
					Message msg=new Message();
					msg.what=0x951;
					msg.obj=fList;
					Public_Attribute.shelfHandler.sendMessage(msg);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFailed(int what, String url, Object tag,
					Exception exception, int responseCode, long networkMillis) {
				Log.i("onFailed", "连接超时");
			}

			@Override
			public void onFinish(int what) {
				// TODO Auto-generated method stub

			}

		});

	}
}
