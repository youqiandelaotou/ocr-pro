package com.xy.utils.image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;

public class ImageEncode {
    // 图像数据，base64编码后进行urlencode，
    // 要求base64编码和urlencode后大小不超过4M，最短边至少15px，最长边最大4096px,支持 jpg/jpeg/png/bmp 格式，当image字段存在时url字段失效
    public static String encode(String path){
        return  base64(path);
    }

    private static String urlEncode(String str) {
        String encoded = "";
        try {
            encoded = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoded;
    }

    private static String base64(String path) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(data);
    }
}
