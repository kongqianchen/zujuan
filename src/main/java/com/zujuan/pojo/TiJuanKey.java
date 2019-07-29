package com.zujuan.pojo;

public class TiJuanKey {
    private Integer shitiId;

    private Integer shijuanId;

    public TiJuanKey(Integer shitiId, Integer shijuanId) {
        this.shitiId = shitiId;
        this.shijuanId = shijuanId;
    }

    public TiJuanKey() {
        super();
    }

    public Integer getShitiId() {
        return shitiId;
    }

    public void setShitiId(Integer shitiId) {
        this.shitiId = shitiId;
    }

    public Integer getShijuanId() {
        return shijuanId;
    }

    public void setShijuanId(Integer shijuanId) {
        this.shijuanId = shijuanId;
    }
}