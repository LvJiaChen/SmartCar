package com.example.qr_codescan.do_server;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.example.qr_codescan.SQLite.Insert_Scan;
import com.example.qr_codescan.fragment.LoadImage;
import com.example.qr_codescan.public_.Public_Attribute;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

public class Do_post {
	RequestQueue queue = NoHttp.newRequestQueue();
	String scan_result = Public_Attribute.Scan_Result;
	String path = "http://192.168.43.143:8080/ggg/Scan_Servlet";

	public void do_post(final Context context) {
		Request<String> request = NoHttp.createStringRequest(path,
				RequestMethod.POST);
		request.add("shape_code", scan_result);
		queue.add(44, request, new OnResponseListener<String>() {

			@Override
			public void onStart(int what) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSucceed(int what, Response<String> response) {
				Log.i("succe","成功联网");
				String content = response.get();
				JSONObject jObject;
				try {
					jObject = new JSONObject(content);

					Public_Attribute.price = jObject.optDouble("price");
					Public_Attribute.trade_name = jObject
							.optString("trade_name");
					Public_Attribute.image = jObject.optString("image");
					Log.i("onFailed", Public_Attribute.trade_name);
					new LoadImage(context).loadImg(Public_Attribute.image);
					new Insert_Scan(context).insert();
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
