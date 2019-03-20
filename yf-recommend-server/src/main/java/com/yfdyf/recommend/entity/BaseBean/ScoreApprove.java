package com.yfdyf.recommend.entity.BaseBean;

/**
 *
 * */
public class ScoreApprove {
    private String uuid;

    private String personCode; //员工编号

    private String countDate;//统计时间 年月日

    private String countScore;//积分

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode == null ? null : personCode.trim();
    }

    public String getCountDate() {
        return countDate;
    }

    public void setCountDate(String countDate) {
        this.countDate = countDate == null ? null : countDate.trim();
    }

    public String getCountScore() {
        return countScore;
    }

    public void setCountScore(String countScore) {
        this.countScore = countScore == null ? null : countScore.trim();
    }
}