package com.xy;

import com.xy.utils.image.ImageEncode;
import org.junit.Test;

public class ImageEncodeTest {
    @Test
    public void encode(){
        String path = "/Users/xuyi/Desktop/table1.png";
        String encode = ImageEncode.encode(path);
        System.out.println(encode);
    }
}
