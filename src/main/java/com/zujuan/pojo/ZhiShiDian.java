package com.zujuan.pojo;

public class ZhiShiDian {
    private Integer pointId;

    private Integer parentId;

    private String pointName;

    private Boolean statusResult;

    public ZhiShiDian(Integer pointId, Integer parentId, String pointName, Boolean statusResult) {
        this.pointId = pointId;
        this.parentId = parentId;
        this.pointName = pointName;
        this.statusResult = statusResult;
    }

    public ZhiShiDian() {
        super();
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName == null ? null : pointName.trim();
    }

    public Boolean getStatusResult() {
        return statusResult;
    }

    public void setStatusResult(Boolean statusResult) {
        this.statusResult = statusResult;
    }
}