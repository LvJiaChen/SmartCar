package com.example.qr_codescan.do_server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.example.qr_codescan.fragment.LoadImage;
import com.example.qr_codescan.fragment.MyAdapter;
import com.example.qr_codescan.public_.FavoratePojo;
import com.example.qr_codescan.public_.Public_Attribute;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

public class FavorateServlet {
	private List<Map<String, Object>> data;
	RequestQueue queue = NoHttp.newRequestQueue();
	String path = "http://192.168.43.143:8080/ggg/FavorateServlet";
	List<FavoratePojo> fList = new ArrayList<FavoratePojo>();
	private MyAdapter myAdapter;
	public void fPost(final Context context) {
		Request<String> request = NoHttp.createStringRequest(path,
				RequestMethod.POST);
		request.add("fav", "1");
		queue.add(55, request, new OnResponseListener<String>() {

			@Override
			public void onStart(int what) {
				// TODO Auto-generated method stub

			}

			public void onSucceed(int what, Response<String> response) {
				Log.i("联网成功", "优惠商品");
				String content = response.get();
				try {
					JSONArray jArray = new JSONArray(content);
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject jsonObject = jArray.getJSONObject(i);
						FavoratePojo fPojo = new FavoratePojo();
						fPojo.setFavorate_info(jsonObject
								.optString("favorate_name"));
						fPojo.setFavorate_price(jsonObject
								.optDouble("favorate_price"));
						fPojo.setPrice(jsonObject.optDouble("price"));
						fPojo.setImage(jsonObject.optString("image"));
						fPojo.setX(jsonObject.optInt("X"));
						fPojo.setY(jsonObject.optInt("Y"));
						fList.add(fPojo);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Public_Attribute.fList = fList;
				data = new LoadImage(context).buildData();
				myAdapter = new MyAdapter(context, data);
				Public_Attribute.listView.setAdapter(myAdapter);
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
