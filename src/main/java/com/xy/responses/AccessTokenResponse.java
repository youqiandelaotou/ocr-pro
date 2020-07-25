package com.xy.responses;

import com.alibaba.fastjson.annotation.JSONField;

public class AccessTokenResponse {
    @JSONField(name = "refresh_token")
    private String refreshToken;
    @JSONField(name = "expires_in")
    private long expireIn;
    @JSONField(name = "session_key")
    private String sessionKey;
    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "scope")
    private String scope;
    @JSONField(name = "session_secret")
    private String sessionSecrete;

    @Override
    public String toString() {
        return "AccessTokenResponse{" +
                "refreshToken='" + refreshToken + '\'' +
                ", expireIn=" + expireIn +
                ", sessionKey='" + sessionKey + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", scope='" + scope + '\'' +
                ", sessionSecrete='" + sessionSecrete + '\'' +
                '}';
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(long expireIn) {
        this.expireIn = expireIn;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSessionSecrete() {
        return sessionSecrete;
    }

    public void setSessionSecrete(String sessionSecrete) {
        this.sessionSecrete = sessionSecrete;
    }
}
