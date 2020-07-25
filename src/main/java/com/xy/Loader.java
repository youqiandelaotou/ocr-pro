package com.xy;

import com.xy.requests.OcrNormalReq;
import com.xy.requests.OcrTableReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loader {
    private final Logger logger = LoggerFactory.getLogger(Loader.class);

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("please enter 2 parameters: image url & type[basic/table]");
            System.out.println("example: java -jar orc-pro.jar /Users/xuyi/Desktop/e.png basic ");
            return;
        }
        String path = args[0];
        String typeStr = args[1];
        if (!typeStr.equals("table")) {
            System.out.println("type should be in normal or table");
            return;
        }
        OcrType type = OcrType.valueOf(typeStr);
        Loader loader = new Loader();
        loader.run(path, type);
    }

    public void run(String path, OcrType type) throws Exception {
        System.out.println("request path: " + path);
        System.out.println("request type: " + type.toString());
        switch (type) {
            case table:
                OcrTableReq tableReq = new OcrTableReq();
                String tableResult = tableReq.request(path);
                System.out.println("result: " + tableResult);
                break;
            case normal:
                OcrNormalReq req = new OcrNormalReq();
                String result = req.request(path);
                System.out.println("result: " + result);
                break;
            case frequency:
                // TODO
                break;
            default:
                throw new Exception("ocr type not support: " + type.name());
        }
    }
}
