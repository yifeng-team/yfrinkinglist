package com.yfdyf.recommend.entity.BaseBean;

/**
 * 基础人员
 * */
public class BasePerson {
    private String personCode;

    private String personName;

    private String positionCode;

    private String positionName;

    private String imgHead;

    private String totalScore;

    private String totalGrading;

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode == null ? null : personCode.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public String getImgHead() {
        return imgHead;
    }

    public void setImgHead(String imgHead) {
        this.imgHead = imgHead == null ? null : imgHead.trim();
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore == null ? null : totalScore.trim();
    }

    public String getTotalGrading() {
        return totalGrading;
    }

    public void setTotalGrading(String totalGrading) {
        this.totalGrading = totalGrading == null ? null : totalGrading.trim();
    }
}