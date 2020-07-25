package com.xy.requests;

public interface OcrReq {
    public String request(String path) throws Exception;
    public String refreshAccessToken() throws Exception;
    public String getAccessToken();
    public String getUrl(String urlPrefix) throws Exception;
}
