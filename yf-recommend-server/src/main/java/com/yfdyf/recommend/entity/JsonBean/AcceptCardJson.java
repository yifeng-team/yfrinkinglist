package com.yfdyf.recommend.entity.JsonBean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcceptCardJson {//认可卡

    private String DEPARTMENT_NAME;//公司

    private String PERSON_ID;//工号

    private String NUM;//排名

    private String INTEGRAL;//数量

    private String NAME;//姓名

    public String getDEPARTMENT_NAME() {
        return DEPARTMENT_NAME;
    }

    public void setDEPARTMENT_NAME(String DEPARTMENT_NAME) {
        this.DEPARTMENT_NAME = DEPARTMENT_NAME;
    }

    public String getPERSON_ID() {
        return PERSON_ID;
    }

    public void setPERSON_ID(String PERSON_ID) {
        this.PERSON_ID = PERSON_ID;
    }

    public String getINTEGRAL() {
        return INTEGRAL;
    }

    public void setINTEGRAL(String INTEGRAL) {
        this.INTEGRAL = INTEGRAL;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getNUM() {
        return NUM;
    }

    public void setNUM(String NUM) {
        this.NUM = NUM;
    }

    public AcceptCardJson(String DEPARTMENT_NAME, String PERSON_ID, String NUM, String INTEGRAL, String NAME) {
        this.DEPARTMENT_NAME = DEPARTMENT_NAME;
        this.PERSON_ID = PERSON_ID;
        this.NUM = NUM;
        this.INTEGRAL = INTEGRAL;
        this.NAME = NAME;
    }

    public AcceptCardJson() {
    }

    @Override
    public String toString() {
        return "AcceptCardJson{" +
                "DEPARTMENT_NAME='" + DEPARTMENT_NAME + '\'' +
                ", PERSON_ID='" + PERSON_ID + '\'' +
                ", NUM='" + NUM + '\'' +
                ", INTEGRAL='" + INTEGRAL + '\'' +
                ", NAME='" + NAME + '\'' +
                '}';
    }
}
