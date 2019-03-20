package com.yfdyf.recommend.entity.BaseBean;

public class ScoreDevelopment {
    private String uuid;

    private String personCode;

    private String countDate;

    private String countScore;

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