package com.yfdyf.recommend.entity.BaseBean;

/**
 *  360 评估 取平均分（最多5星） 精确到小数后两位
 * */
public class ScoreAssess {
    private String uuid;

    private String personCode;//员工编号

    private String countDate;//统计年月 （不包括日）

    private String cruxplan;//关键计划

    private String technology;

    private String efficiency;

    private String innovatestudy;

    private String teamwork;

    private String communication;

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

    public String getCruxplan() {
        return cruxplan;
    }

    public void setCruxplan(String cruxplan) {
        this.cruxplan = cruxplan == null ? null : cruxplan.trim();
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology == null ? null : technology.trim();
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency == null ? null : efficiency.trim();
    }

    public String getInnovatestudy() {
        return innovatestudy;
    }

    public void setInnovatestudy(String innovatestudy) {
        this.innovatestudy = innovatestudy == null ? null : innovatestudy.trim();
    }

    public String getTeamwork() {
        return teamwork;
    }

    public void setTeamwork(String teamwork) {
        this.teamwork = teamwork == null ? null : teamwork.trim();
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication == null ? null : communication.trim();
    }
}