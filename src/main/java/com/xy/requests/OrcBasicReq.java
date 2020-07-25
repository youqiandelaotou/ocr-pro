package com.xy.requests;

import com.alibaba.fastjson.JSON;
import com.xy.responses.AccessTokenResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
