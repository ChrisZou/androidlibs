package com.chriszou.androidlibs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.chriszou.androidlibs.L.l;

/**
 * Created by Chris on 2/24/15.
 */
public class Downloader {
    private final Activity mContext;
    private final String mUrl;
    private final String mOutputPath;

    public Downloader(Activity context, String url, String outputPath) {
        mContext = context;
        this.mUrl = url;
        this.mOutputPath = outputPath;
    }

    public void start() {
        l("start download from: %s, to: %s", mUrl, mOutputPath);
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new UpgradeTask().execute(mUrl);
            }
        });
    }

    private class UpgradeTask extends AsyncTask<String, Integer, Boolean> {
        public UpgradeTask() {}

        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setMessage(mContext.getString(R.string.msg_downloading));
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean succeed) {
            mProgressDialog.dismiss();
            if (mOnDownloadCompleteListener != null) {
                mOnDownloadCompleteListener.onDownloadComplete(succeed, mOutputPath);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int percent = values[0];
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(percent);
        }

        @Override
        protected Boolean doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    //return "Server returned HTTP " + connection.getResponseCode()+ " " + connection.getResponseMessage();
                    return false;
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(mOutputPath);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return false;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) {
                        // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    }
                    output.write(data, 0, count);
                }
                return true;
            } catch (Exception e) {
                return false;
            } finally {
                try {
                    if (output != null) output.close();
                    if (input != null) input.close();
                } catch (IOException ignored) {}
                if (connection != null) connection.disconnect();
            }
        }
    }

    private OnDownloadCompleteListener mOnDownloadCompleteListener;

    public void setOnDownloadCompleteListener(OnDownloadCompleteListener listener) {
        mOnDownloadCompleteListener = listener;
    }

    public static interface OnDownloadCompleteListener {
        public void onDownloadComplete(Boolean succeed, String outputPath);
    }
}
