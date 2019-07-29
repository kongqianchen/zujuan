package com.zujuan.pojo;

public class ST_ZSD extends ST_ZSDKey {
    private Integer statusReview;

    public ST_ZSD(Integer pointId, Integer shitiId, Integer statusReview) {
        super(pointId, shitiId);
        this.statusReview = statusReview;
    }

    public ST_ZSD() {
        super();
    }

    public Integer getStatusReview() {
        return statusReview;
    }

    public void setStatusReview(Integer statusReview) {
        this.statusReview = statusReview;
    }
}