/**
 *
 */
package com.chriszou.androidlibs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Util class for reading/writing InputStream/OutputStream
 * @author Chris
 *
 */
public class StreamUtil {
    /**
     * Read from the InputStream and return as a String
     * @param is
     * @return
     * @throws IOException
     */
	public static String getString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        String result = baos.toString();
        baos.close();
        return result;
	}
}
