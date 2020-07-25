package com.xy.requests;

import com.alibaba.fastjson.JSON;
import com.xy.responses.TableOcrResponse;
import com.xy.utils.image.ImageEncode;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OcrTableReq extends OrcBasicReq{

    private final Logger logger = LoggerFactory.getLogger(OcrTableReq.class);
    private final String urlPrefix = "https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/request";

    public String request(String path) throws Exception {
        // step1 get url
        String url = getUrl(urlPrefix);
        // step2 gen params
        String encodedImage = ImageEncode.encode(path);
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        NameValuePair[] data = {
                new NameValuePair("image", encodedImage),
                new NameValuePair("is_sync", "true"),
                new NameValuePair("request_type", "excel"),
        };
        postMethod.setRequestBody(data);
        // step3 send request
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(postMethod);
        String result = new String(postMethod.getResponseBody(), "utf-8");
        logger.info("TableOcrResponse: "  +  result);
        // step4 parse result
        TableOcrResponse resp;
        try {
            resp = JSON.parseObject(result, TableOcrResponse.class);
        } catch (Exception e) {
            logger.info("parse TableOcrResponse failed");
            e.printStackTrace();
            throw e;
        }
        // step5 check ret code
        if (resp.getResult().getRetCode() != 3){
            throw new Exception("OcrTableReq return code: " + resp.getResult().getRetCode());
        }
        return resp.getResult().getResultData();

    }
}
