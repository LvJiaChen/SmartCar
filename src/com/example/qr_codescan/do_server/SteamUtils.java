package com.example.qr_codescan.do_server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SteamUtils {
	public static String getStringFromSteam(InputStream is){
		String content="";
		int len=-1;
		byte[] buf=new byte[1024];
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		
		try {
			while((len= is.read(buf))!=-1){
				baos.write(buf,0,len);
			}
			content=baos.toString();
			baos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}
}
