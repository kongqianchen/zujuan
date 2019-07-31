package com.zujuan.vo;

import com.zujuan.pojo.ZhiShiDian;

import java.util.Date;
import java.util.List;

/**
 * @Author Guojian Wang
 * @Date 2019/7/31 9:36
 */
public class ST_ZSDVo {
    private Integer shitiId;

    private String tixing;

    private String tilei;

    private String nandu;

    private Integer referenceTime;

    private String years;

    private String city;

    private String source;

    private String problem;

    private String answer;

    private String analysis;

    private Date commitTime;

    private List<ZhiShiDian> zhiShiDians;

    public Integer getShitiId() {
        return shitiId;
    }

    public void setShitiId(Integer shitiId) {
        this.shitiId = shitiId;
    }

    public String getTixing() {
        return tixing;
    }

    public void setTixing(String tixing) {
        this.tixing = tixing;
    }

    public String getTilei() {
        return tilei;
    }

    public void setTilei(String tilei) {
        this.tilei = tilei;
    }

    public String getNandu() {
        return nandu;
    }

    public void setNandu(String nandu) {
        this.nandu = nandu;
    }

    public Integer getReferenceTime() {
        return referenceTime;
    }

    public void setReferenceTime(Integer referenceTime) {
        this.referenceTime = referenceTime;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public List<ZhiShiDian> getZhiShiDians() {
        return zhiShiDians;
    }

    public void setZhiShiDians(List<ZhiShiDian> zhiShiDians) {
        this.zhiShiDians = zhiShiDians;
    }
}
