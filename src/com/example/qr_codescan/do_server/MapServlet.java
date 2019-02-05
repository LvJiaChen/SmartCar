package com.example.qr_codescan.do_server;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.example.qr_codescan.SQLite.InsertMapSearch;
import com.example.qr_codescan.public_.Commodity;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

public class MapServlet {
	RequestQueue queue=NoHttp.newRequestQueue();
	String path="http://192.168.43.143:8080/ggg/MapServlet";
	List<Commodity> CList=new ArrayList<Commodity>();
	public void post(final Context context){
		Request<String> request = NoHttp.createStringRequest(path,
				RequestMethod.POST);
		request.add("t","1");
		queue.add(66, request, new OnResponseListener<String>() {

			@Override
			public void onStart(int what) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSucceed(int what, Response<String> response) {
				String content=response.get();
				try {
					JSONArray jArray=new JSONArray(content);
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject jObject=jArray.getJSONObject(i);
						Commodity comm=new Commodity();
						comm.setTrade_name(jObject.optString("trade_name"));
						comm.setX(jObject.optInt("X"));
						comm.setY(jObject.optInt("Y"));
						CList.add(comm);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new InsertMapSearch(context).insert(CList);
				
			}

			@Override
			public void onFailed(int what, String url, Object tag,
					Exception exception, int responseCode, long networkMillis) {
				Log.i("联网失败","11111111");
				
			}

			@Override
			public void onFinish(int what) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
}
