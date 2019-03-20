package com.yfdyf.recommend.entity.BaseBean;

/**
 * 工作时长 出勤
 * */
public class TimeWorking {
    private String uuid;

    private String personCode;

    private String countDate;

    private String countTime;

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

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime == null ? null : countTime.trim();
    }
}