package com.zujuan.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TiJuan extends TiJuanKey {
    private Date zujuanTime;

    private Integer quesNumber;

    private BigDecimal fraction;

    public TiJuan(Integer shitiId, Integer shijuanId, Date zujuanTime, Integer quesNumber, BigDecimal fraction) {
        super(shitiId, shijuanId);
        this.zujuanTime = zujuanTime;
        this.quesNumber = quesNumber;
        this.fraction = fraction;
    }

    public TiJuan() {
        super();
    }

    public Date getZujuanTime() {
        return zujuanTime;
    }

    public void setZujuanTime(Date zujuanTime) {
        this.zujuanTime = zujuanTime;
    }

    public Integer getQuesNumber() {
        return quesNumber;
    }

    public void setQuesNumber(Integer quesNumber) {
        this.quesNumber = quesNumber;
    }

    public BigDecimal getFraction() {
        return fraction;
    }

    public void setFraction(BigDecimal fraction) {
        this.fraction = fraction;
    }
}