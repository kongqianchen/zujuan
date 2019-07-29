package com.zujuan.pojo;

public class LongContent {
    private Integer zhizhenId;

    private byte[] content;

    public LongContent(Integer zhizhenId, byte[] content) {
        this.zhizhenId = zhizhenId;
        this.content = content;
    }

    public LongContent() {
        super();
    }

    public Integer getZhizhenId() {
        return zhizhenId;
    }

    public void setZhizhenId(Integer zhizhenId) {
        this.zhizhenId = zhizhenId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}