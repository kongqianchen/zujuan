package com.zujuan.pojo;

public class ST_ZSDKey {
    private Integer pointId;

    private Integer shitiId;

    public ST_ZSDKey(Integer pointId, Integer shitiId) {
        this.pointId = pointId;
        this.shitiId = shitiId;
    }

    public ST_ZSDKey() {
        super();
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getShitiId() {
        return shitiId;
    }

    public void setShitiId(Integer shitiId) {
        this.shitiId = shitiId;
    }
}