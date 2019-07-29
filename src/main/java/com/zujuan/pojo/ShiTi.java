package com.zujuan.pojo;

import java.util.Date;

public class ShiTi {
    private Integer shitiId;

    private String tixing;

    private String tilei;

    private String nandu;

    private Integer referenceTime;

    private Date years;

    private String city;

    private String source;

    private String problem;

    private String answer;

    private String analysis;

    private Date commitTime;

    public ShiTi(Integer shitiId, String tixing, String tilei, String nandu, Integer referenceTime, Date years, String city, String source, String problem, String answer, String analysis, Date commitTime) {
        this.shitiId = shitiId;
        this.tixing = tixing;
        this.tilei = tilei;
        this.nandu = nandu;
        this.referenceTime = referenceTime;
        this.years = years;
        this.city = city;
        this.source = source;
        this.problem = problem;
        this.answer = answer;
        this.analysis = analysis;
        this.commitTime = commitTime;
    }

    public ShiTi() {
        super();
    }

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
        this.tixing = tixing == null ? null : tixing.trim();
    }

    public String getTilei() {
        return tilei;
    }

    public void setTilei(String tilei) {
        this.tilei = tilei == null ? null : tilei.trim();
    }

    public String getNandu() {
        return nandu;
    }

    public void setNandu(String nandu) {
        this.nandu = nandu == null ? null : nandu.trim();
    }

    public Integer getReferenceTime() {
        return referenceTime;
    }

    public void setReferenceTime(Integer referenceTime) {
        this.referenceTime = referenceTime;
    }

    public Date getYears() {
        return years;
    }

    public void setYears(Date years) {
        this.years = years;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem == null ? null : problem.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis == null ? null : analysis.trim();
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }
}