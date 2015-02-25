/**
 *
 */
package com.chriszou.androidlibs;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Util class for handling http request and URL content loading, etc.
 * @author Chris
 *
 */
public class HttpUtils {

    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public static final String CONTENT_TYPE_JSON = "application/json";
    /**
     *
     * Send a Post request to the url with the given json.
     * @param url
     * @param jsonString
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
	public static HttpResponse postJson(String url, String jsonString) throws ClientProtocolException, IOException {
        return postJson(url, jsonString, Collections.EMPTY_LIST);
	}

    /**
     * Send a Post request to the url with the given json.
     * @param url
     * @param jsonString
     * @param extraHeaders extra headers to be added to the request
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static HttpResponse postJson(String url, String jsonString, List<Header> extraHeaders) throws ClientProtocolException, IOException {

        HttpPost post = new HttpPost(url);
        post.setHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
        post.setEntity(new StringEntity(jsonString, "UTF-8"));
        for (Header header:extraHeaders) {
            post.setHeader(header);
        }

        HttpClient client = timeoutHttpClient();

        HttpResponse response = client.execute(post);
        return response;
    }

    /**
     * Get the body from the HttpResponse and return as a String
     * @param response
     * @return
     * @throws IOException
     */
    public static String responseToString(HttpResponse response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuilder builder = new StringBuilder();
        for (String line = null; (line = reader.readLine()) != null;) {
            builder.append(line).append("\n");
        }
        return builder.toString().trim();
    }

    /**
     * Perform a delete request
     */
    public static HttpResponse deleteRequest(String url) throws IOException {
        HttpDelete deleteRequest = new HttpDelete(url);
        HttpClient client = timeoutHttpClient();
        return client.execute(deleteRequest);
    }

    /**
     * Get the newest ETAG in the response to the url
     *
     * @param url
     */
    public static String getEtag(String url) throws IOException {
        HttpClient client = timeoutHttpClient();
        HttpHead head = new HttpHead(url);
        HttpResponse response = client.execute(head);
        Header etagHeader = response.getFirstHeader("ETag");
        String etag = null;
        if(etagHeader!=null) {
            etag = etagHeader.getValue();
        }
        return etag;
    }

    /**
     * Get the content at the url as a String
     */
    public static String getContent(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        InputStream is = url.openStream();
        String content = StreamUtil.getString(is);
        is.close();

        return content;
    }

    /**
     * A DefaultHttpClient with 10 seconds of timeout
     * @return
     */
    private static DefaultHttpClient timeoutHttpClient() {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 5 * 1000);
        HttpConnectionParams.setSoTimeout(params, 5 * 1000);
        DefaultHttpClient client = new DefaultHttpClient(params);

        return client;
    }

    /**
     * If an HttpResponse indicate OK
     * @param response
     * @return
     */
    public static boolean responseOK(HttpResponse response) {
        if (response == null || response.getStatusLine() == null) {
            return false;
        }

        return response.getStatusLine().getStatusCode() == 200;
    }
}

