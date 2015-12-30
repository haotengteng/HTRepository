package com.model;

import java.util.Date;

/**
 * Created by htt on 2015/12/28.
 */
public class TokenDO {
    public String tokenId;
    public String userId;
    public String userName;
    public String uri;
    public Date registerTime;


    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public Long getLongDate(){
       return getRegisterTime().getTime();
    }
}
