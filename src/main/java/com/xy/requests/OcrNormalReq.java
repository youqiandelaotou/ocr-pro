package com.xy.requests;

import com.alibaba.fastjson.JSON;
import com.xy.responses.NormalOcrResponse;
import com.xy.utils.image.ImageEncode;
import com.xy.utils.position.PositionUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OcrNormalReq extends OrcBasicReq {

    private final Logger logger = LoggerFactory.getLogger(OcrNormalReq.class);
    private final String urlPrefix = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

    public String request(String path) throws Exception {
        // step1 get url
        String url = getUrl(urlPrefix);
        // step2 gen params
        String encodedImage = ImageEncode.encode(path);
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        NameValuePair[] data = {
                new NameValuePair("image", encodedImage),
                new NameValuePair("probability", "true")
        };
        postMethod.setRequestBody(data);
        // step3 send request
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(postMethod);
        String result = new String(postMethod.getResponseBody(), "utf-8");
        logger.info("NormalOcrResponse: \n"  +  result);
        // step4 parse result
        NormalOcrResponse resp;
        try {
            resp = JSON.parseObject(result, NormalOcrResponse.class);
        } catch (Exception e) {
            logger.info("parse OcrNormalReq failed");
            e.printStackTrace();
            throw e;
        }
        NormalOcrResponse.Words[] wordsArr = resp.getWordResult();
        // step5 return string
        String ret = "";
        if (wordsArr != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (NormalOcrResponse.Words words : wordsArr) {
                stringBuilder.append(words.getWords());
                stringBuilder.append("\n");
            }
            ret =  stringBuilder.toString();
        }
        // step6 return with position
        String position = PositionUtil.getPosition(0, 0);
        return ret + "\n\n" + "position: " + position;
    }

}
