package com.example.qr_codescan.suanfa;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.qr_codescan.do_server.WeiZhiServlet;

import android.content.Context;
import android.graphics.PointF;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.SystemClock;

public class WeiZhi extends Thread {
	private Context context;
	private List<ScanResult> scanResultsList;
	private WifiManager wifiManager;
	private int ARss, BRss, CRss, DRss,ERss,FRss;
	public volatile boolean exit = false;

	public WeiZhi(Context context) {
		this.context=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		while (!exit) {
			SystemClock.sleep(1000);
			wifiManager.startScan();
			scanResultsList = wifiManager.getScanResults();
			for (int j = 0; j < scanResultsList.size(); j++) {
				switch (scanResultsList.get(j).SSID) {
				case "AWifi":
					ARss = scanResultsList.get(j).level;
					break;
				case "BWifi":
					BRss = scanResultsList.get(j).level;
					break;
				case "CWifi":
					CRss = scanResultsList.get(j).level;
					break;
				case "DWifi":
					DRss = scanResultsList.get(j).level;
					break;
				case "EWifi":
					ERss = scanResultsList.get(j).level;
					break;
				case "FWifi":
					FRss = scanResultsList.get(j).level;
					break;
				}
			}
			JSONObject jObject = new JSONObject();
			try {
				jObject.put("AWifi", ARss);
				jObject.put("BWifi", BRss);
				jObject.put("CWifi", CRss);
				jObject.put("DWifi", DRss);
				jObject.put("EWifi", ERss);
				jObject.put("FWifi", FRss);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (jObject.toString()!=null&&jObject.toString().length()>0) {
				new WeiZhiServlet().do_post(jObject.toString());
			}
			
		}

	}
}
