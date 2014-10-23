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
import android.os.Looper;

/**
 * Class for loading content from a given url
 * @author zouyong
 *
 */
public class UrlContentLoader {
	private String mUrl;
	public UrlContentLoader(String url) {
		mUrl = url;
	}

	/**
	 * This will be executed in the same thread as the caller.
	 * @throws IOException
	 */
	public String executeSync() throws IOException{
		URL url = new URL(mUrl);
		InputStream is = url.openStream();
		String contentString = StreamUtil.getString(is);
		is.close();
		return contentString;
	}

	/**
	 * This should be called from UI thread since the actual execution will be in a different thread.
	 * @param callBack
	 */
	public void execute(final Callback callBack) {
		final Handler handler = new Handler(Looper.getMainLooper());

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					URL url = new URL(mUrl);
					InputStream is = url.openStream();
					final String contentString = StreamUtil.getString(is);
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


	/**
	 * Callback listener for loading url content
	 * @author zouyong
	 *
	 */
	public static interface Callback {
		public void onSucceed(String content);
		public void onFailed(String msg);
		public void onCanceld();
	}

	/**
	 * A {@link Callback} that only cares when the loading process succeed
	 * @author zouyong
	 *
	 */
	public static abstract class SucceedCallback implements Callback {
		@Override
		public abstract void onSucceed(String content);

		@Override
		public void onFailed(String msg) {  L.e("Error when loading content: "+msg); }

		@Override
		public void onCanceld() {}
	}
}
