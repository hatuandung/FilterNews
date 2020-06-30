package com.example.ex7t3hhomework.file;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileManager {
    String TAG = "FileManager";
    String rootPath = Environment.getExternalStorageDirectory().getPath();

    public String getRootPath() {
        return rootPath;
    }

    public File[] getFile(String path) {
        File file = new File(path);
        return file.listFiles();
    }

    public void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }

    public String download(String link) {
        try {

            String fileName = link.replaceAll("[-_./:?=]", "");
            rootPath +=  "/FilterNews/" + fileName + ".html";

            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            byte[] b = new byte[1024];
            int count = inputStream.read(b);

            File file = new File(rootPath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (count > 0) {
                fileOutputStream.write(b, 0, count);
                count = inputStream.read(b);
            }
            inputStream.close();
            fileOutputStream.close();
            //return path;
            //Log.e(TAG, "download: ", rootPath);
            //System.out.println(rootPath);
            return rootPath;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
