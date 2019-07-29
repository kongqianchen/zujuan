package com.zujuan.pojo;

public class ST_ZSD extends ST_ZSDKey {
    private Boolean statusReview;

    public ST_ZSD(Integer pointId, Integer shitiId, Boolean statusReview) {
        super(pointId, shitiId);
        this.statusReview = statusReview;
    }

    public ST_ZSD() {
        super();
    }

    public Boolean getStatusReview() {
        return statusReview;
    }

    public void setStatusReview(Boolean statusReview) {
        this.statusReview = statusReview;
    }
}