package com.zujuan.pojo;

public class JianSuo {
    private Integer jiansuoId;

    private String dengji;

    private String xueke;

    private String nianji;

    private String zhangjie;

    private Integer pointId;

    public JianSuo(Integer jiansuoId, String dengji, String xueke, String nianji, String zhangjie, Integer pointId) {
        this.jiansuoId = jiansuoId;
        this.dengji = dengji;
        this.xueke = xueke;
        this.nianji = nianji;
        this.zhangjie = zhangjie;
        this.pointId = pointId;
    }

    public JianSuo() {
        super();
    }

    public Integer getJiansuoId() {
        return jiansuoId;
    }

    public void setJiansuoId(Integer jiansuoId) {
        this.jiansuoId = jiansuoId;
    }

    public String getDengji() {
        return dengji;
    }

    public void setDengji(String dengji) {
        this.dengji = dengji == null ? null : dengji.trim();
    }

    public String getXueke() {
        return xueke;
    }

    public void setXueke(String xueke) {
        this.xueke = xueke == null ? null : xueke.trim();
    }

    public String getNianji() {
        return nianji;
    }

    public void setNianji(String nianji) {
        this.nianji = nianji == null ? null : nianji.trim();
    }

    public String getZhangjie() {
        return zhangjie;
    }

    public void setZhangjie(String zhangjie) {
        this.zhangjie = zhangjie == null ? null : zhangjie.trim();
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }
}