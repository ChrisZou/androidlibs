package com.chriszou.androidlibs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A util class for read/write/append text from/to file
 * Created by zouyong on 10/23/14.
 */
public class FileUtils {

    /**
     * Read the file content and return as String
     * @param path the path of the file
     * @return
     * @throws IOException
     */
    public static String read(String path) throws IOException {
        File file = new File(path);
        StringBuilder text = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            text.append(line);
            text.append('\n');
        }
        br.close();

        return text.toString();
    }

    /**
     * Write text to the file in the given path, will truncate the file if already exists
     * @param path
     * @param text
     * @return true if writing succeed, false otherwise
     * @throws IOException
     */
    public static boolean write(String path, String text) throws IOException {
        return writeFile(path, text, false);
    }

    /**
     * Append text to the end of the file in the given path
     * @param path
     * @param msg
     * @return true if appending succeed, false otherwise
     * @throws IOException
     */
    public static boolean append(String path, String msg) throws IOException {
        return writeFile(path, msg, true);
    }

    /**
     * Write text to the file in the given path
     * @param path
     * @param text
     * @param append Weather to append the text to the file, if set to false, when it truncate it and write from the beginning.
     * @return
     * @throws IOException
     */
    private static boolean writeFile(String path, String text, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(path, append);
        fileWriter.write(text);
        fileWriter.flush();
        fileWriter.close();
        return true;
    }

}


