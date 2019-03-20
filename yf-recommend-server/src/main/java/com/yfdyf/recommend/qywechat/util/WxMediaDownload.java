package com.yfdyf.recommend.qywechat.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WxMediaDownload {

    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static InputStream getInputStream(String accessToken, String mediaId) {
        InputStream is = null;
        String url = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token="
                + accessToken + "&media_id=" + mediaId;
//        https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet
                    .openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            // 获取文件转化为byte流
            is = http.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;

    }

    /**
     *
     * 获取下载图片信息（jpg）
     *
     *
     *
     * @param mediaId
     *
     *            文件的id
     *
     * @throws Exception
     */

    public static void saveImageToDisk(String accessToken, String mediaId, String picName, String picPath)
            throws Exception {
        String fileName = picPath+picName+".jpg";
        File newFile=new File(fileName);
        if(!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdirs();//创建父级文件路径
        }
        InputStream inputStream = getInputStream(accessToken, mediaId);
        byte[] data = new byte[DEFAULT_BUFFER_SIZE];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args) throws Exception {
        String accessToken = "";
        String mediaId = "";
        String picName = "";
        saveImageToDisk(accessToken, mediaId, picName,"f:/");
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
        return output.toByteArray();
    }

    private static long copyLarge(InputStream input, OutputStream output,
                                  byte[] buffer) throws IOException {
        long count = 0;
        int n = 0;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
