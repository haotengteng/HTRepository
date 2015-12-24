package com.model;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by htt on 2015/12/23.
 */
public class UserBaseDO implements Serializable {
    public String userId;
    public String userName;
    public String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
