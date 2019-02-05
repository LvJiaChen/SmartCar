package com.example.qr_codescan.do_server;


import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.qr_codescan.public_.Public_Attribute;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

 
public class Do_Scan_Selecct {
	RequestQueue queue = NoHttp.newRequestQueue();
	String scan_result = Public_Attribute.Scan_Result;
	String path = "http://192.168.43.143:8080/ggg/Scan_Servlet?shape_code="
			+ scan_result;

	public void Scan() {

		Request<String> request = NoHttp.createStringRequest(path,
				RequestMethod.GET);
		queue.add(88, request, new OnResponseListener<String>() {

			@Override
			public void onStart(int what) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSucceed(int what, Response<String> response) {

				// �����ɹ�
				String content = response.get();
				JSONObject jObject;
				try {
					jObject = new JSONObject(content);

					Public_Attribute.price = jObject.optDouble("price");
					Public_Attribute.trade_name = jObject
							.optString("trade_name");
					Log.i("test",Public_Attribute.price+"");
					Log.i("test",Public_Attribute.trade_name+"");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// new Insert_Scan().insert();

			
				
			}

			@Override
			public void onFailed(int what, String url, Object tag,
					Exception exception, int responseCode, long networkMillis) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFinish(int what) {
				
				
			}});

	}
}
