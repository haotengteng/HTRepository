package com.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by htt on 2015/12/23.
 */
public class UserBaseDO implements Serializable {
    public String userId;
    public String userName;
    public String password;
    public String phone;
    public Date registerTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}
