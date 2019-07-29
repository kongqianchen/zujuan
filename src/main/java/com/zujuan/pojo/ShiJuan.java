package com.zujuan.pojo;

import java.math.BigDecimal;

public class ShiJuan {
    private Integer shijuanId;

    private BigDecimal shijuanDifficult;

    private String testType;

    private Integer scanCount;

    private String xueke;

    private String shijuanName;

    private BigDecimal score;

    private Integer author;

    public ShiJuan(Integer shijuanId, BigDecimal shijuanDifficult, String testType, Integer scanCount, String xueke, String shijuanName, BigDecimal score, Integer author) {
        this.shijuanId = shijuanId;
        this.shijuanDifficult = shijuanDifficult;
        this.testType = testType;
        this.scanCount = scanCount;
        this.xueke = xueke;
        this.shijuanName = shijuanName;
        this.score = score;
        this.author = author;
    }

    public ShiJuan() {
        super();
    }

    public Integer getShijuanId() {
        return shijuanId;
    }

    public void setShijuanId(Integer shijuanId) {
        this.shijuanId = shijuanId;
    }

    public BigDecimal getShijuanDifficult() {
        return shijuanDifficult;
    }

    public void setShijuanDifficult(BigDecimal shijuanDifficult) {
        this.shijuanDifficult = shijuanDifficult;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType == null ? null : testType.trim();
    }

    public Integer getScanCount() {
        return scanCount;
    }

    public void setScanCount(Integer scanCount) {
        this.scanCount = scanCount;
    }

    public String getXueke() {
        return xueke;
    }

    public void setXueke(String xueke) {
        this.xueke = xueke == null ? null : xueke.trim();
    }

    public String getShijuanName() {
        return shijuanName;
    }

    public void setShijuanName(String shijuanName) {
        this.shijuanName = shijuanName == null ? null : shijuanName.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }
}