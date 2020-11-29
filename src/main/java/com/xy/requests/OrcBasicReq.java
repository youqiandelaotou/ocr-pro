package com.xy.requests;

import com.alibaba.fastjson.JSON;
import com.xy.responses.AccessTokenResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调用方式一
 * https://aip.baidubce.com/rest/2.0/ocr/v1/general?access_token=24.f9ba9c5241b67688bb4adbed8bc91dec.2592000.1485570332.282335-8574074
 *
 *
 *
 * 调用方式二
 * POST /rest/2.0/face/v1/detect HTTP/1.1
 * accept-encoding: gzip, deflate
 * x-bce-date: 2015-03-24T13:02:00Z
 * connection: keep-alive
 * accept: *
 * host:aip.baidubce.com
 * x-bce-request-id:73c4e74c-3101-4a00-bf44-fe246959c05e
 * content-type:application/x-www-form-urlencoded
 * authorization:bce-auth-v1/46bd9968a6194b4bbdf0341f2286ccce/2015-03-24T13:02:00Z/1800/host;x-bce-date/994014d96b0eb26578e039fa053a4f9003425da4bfedf33f4790882fb4c54903
 */

public abstract class OrcBasicReq implements OcrReq {
    private final Logger logger = LoggerFactory.getLogger(OrcBasicReq.class);
    private final String url = "https://aip.baidubce.com/oauth/2.0/token";
    private final String GrantType = "client_credentials";
    private final String ClientId = "CMMdX9cMwZnvoPGNE560Zu2w";
    private final String ClientSecret = "C5plz6aByVsx750ZMae6E4wzy2xgprTP";
    private String accessToken = "24.72980b32b69c5b859a54fe09fe6c55cc.2592000.1598168205.282335-21561114";

    public String refreshAccessToken() throws Exception {
        GetMethod method = new GetMethod(url);
        NameValuePair[] data = {
                new NameValuePair("grant_type", GrantType),
                new NameValuePair("client_id", ClientId),
                new NameValuePair("client_secret", ClientSecret)
        };
        method.setQueryString(data);
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(method);
        String result = new String(method.getResponseBody(), "utf-8");

        if (result.length() == 0) {
            throw new Exception("access token refresh result is empty");
        }
        AccessTokenResponse resp = null;
        try {
            resp = JSON.parseObject(result, AccessTokenResponse.class);
        } catch (Exception e) {
            System.out.println("parse AccessTokenResponse failed");
            e.printStackTrace();
            throw e;
        }
        accessToken = resp.getAccessToken();
        logger.info("refresh accessToken: " + accessToken);
        return accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getUrl(String urlPrefix) throws Exception {
        String accessToken = refreshAccessToken();
        return urlPrefix + "?access_token=" + accessToken;
    }
}
