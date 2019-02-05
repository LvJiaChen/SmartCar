package com.example.qr_codescan.fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.qr_codescan.public_.FavoratePojo;
import com.example.qr_codescan.public_.Public_Attribute;

public class LoadImage {
	Context context;
	public LoadImage(Context context) {
		this.context=context;
	}
	public void loadImg(final String imageURL) {
		String imageName = imageURL.substring(37);
		File cacheDir = context.getCacheDir();
		final File imFile = new File(cacheDir, imageName);
		Public_Attribute.imFile = "/data/data/com.example.qr_codescan/cache/"
				+ imageName;
		if (imFile.exists() && imFile.length() > 0) {

		} else {
			new Thread() {
				public void run() {
					try {
						URL url = new URL(imageURL);
						HttpURLConnection conn = (HttpURLConnection) url
								.openConnection();
						conn.setRequestMethod("GET");
						conn.setConnectTimeout(5000);
						int code = conn.getResponseCode();
						if (code == 200) {
							InputStream is = conn.getInputStream();

							FileOutputStream fos = new FileOutputStream(imFile);
							int len = -1;
							byte[] buf = new byte[1024];
							while ((len = is.read(buf)) != -1) {
								fos.write(buf, 0, len);
							}
							fos.close();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};

			}.start();
		}

	}
	public List<Map<String, Object>> buildData() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < Public_Attribute.fList.size(); i++) {
			FavoratePojo f = Public_Attribute.fList.get(i);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fInfo", f.getFavorate_info());
			map.put("fPrice", f.getFavorate_price());
			map.put("price", f.getPrice());
			map.put("X", f.getX());
			map.put("Y", f.getY());
			loadImg(f.getImage());
			Bitmap bitmap = BitmapFactory.decodeFile(Public_Attribute.imFile);
			map.put("img", bitmap);
			data.add(map);

		}
		return data;
	}
}
