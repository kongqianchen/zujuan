package com.zujuan.pojo;

public class ZhiShiDian {
    private Integer pointId;

    private Integer parentId;

    private String pointName;

    private Integer statusReview;

    public ZhiShiDian(Integer pointId, Integer parentId, String pointName, Integer statusReview) {
        this.pointId = pointId;
        this.parentId = parentId;
        this.pointName = pointName;
        this.statusReview = statusReview;
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

    public Integer getStatusReview() {
        return statusReview;
    }

    public void setStatusReview(Integer statusReview) {
        this.statusReview = statusReview;
    }
}