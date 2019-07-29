package com.zujuan.pojo;

import java.util.Date;

public class Log {
    private Integer logId;

    private String userPhone;

    private Date createTime;

    private String message;

    public Log(Integer logId, String userPhone, Date createTime, String message) {
        this.logId = logId;
        this.userPhone = userPhone;
        this.createTime = createTime;
        this.message = message;
    }

    public Log() {
        super();
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}