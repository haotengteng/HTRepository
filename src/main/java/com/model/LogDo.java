package com.model;

/**
 * Created by Administrator on 2016/1/4.
 */
public class LogDo {
    public String logId;
    public String userId;
    public String uri;
    public String logDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getLogId() {

        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getlogDate() {
        return logDate;
    }

    public void setlogDate(String date) {
        this.logDate = date;
    }
}
