/**
 * UrlContentLoader.java
 * 
 * Created by zouyong on Aug 1, 2014,2014
 */
package com.chriszou.androidlibs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.os.Handler;

/**
 * @author zouyong
 *
 */
public class UrlContentLoader {
    private String mUrl;
    public UrlContentLoader(String url) {
    	mUrl = url;
    }
    
	public void execute(final CallBack callBack) {
        final Handler handler = new Handler();
        
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
                try {
					URL url = new URL(mUrl);
                    InputStream is = url.openStream();
                    final String contentString = inputStreamToString(is);
                    is.close();
                    
                    Runnable run = new Runnable() {
						@Override
						public void run() {
                            callBack.onSucceed(contentString);
						}
					};
                    handler.post(run);
                    
				} catch (final Exception e) {
					e.printStackTrace();
					Runnable run = new Runnable() {
						@Override
						public void run() {
	                        callBack.onFailed(e.getMessage());
						}
					};
	                handler.post(run);
				} 
			}
		};
        
		new Thread(runnable).start();
	}
    
	
	// Slow Implementation
	private String inputStreamToString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line = "";

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		// Read response until the end
		while ((line = rd.readLine()) != null) {
            sb.append(line);
		}

		// Return full string
		return sb.toString();
	}
	
	public static interface CallBack {
        public void onSucceed(String content);
        public void onFailed(String msg);
        public void onCanceld();
	}
}
