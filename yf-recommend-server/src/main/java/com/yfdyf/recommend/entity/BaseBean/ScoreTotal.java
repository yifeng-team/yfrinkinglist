package com.yfdyf.recommend.entity.BaseBean;

public class ScoreTotal {
    private String uuid;

    private String personCode;

    private String countDate;

    private String ranking;

    private String totalScore;

    private String developerationScore;

    private String workingtimeScore;

    private String approveScore;

    private String cruxplanScore;

    private String technologyScore;

    private String efficiencyScore;

    private String innovatestudyScore;

    private String teamworkScore;

    private String communicationScore;

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

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking == null ? null : ranking.trim();
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore == null ? null : totalScore.trim();
    }

    public String getDeveloperationScore() {
        return developerationScore;
    }

    public void setDeveloperationScore(String developerationScore) {
        this.developerationScore = developerationScore == null ? null : developerationScore.trim();
    }

    public String getWorkingtimeScore() {
        return workingtimeScore;
    }

    public void setWorkingtimeScore(String workingtimeScore) {
        this.workingtimeScore = workingtimeScore == null ? null : workingtimeScore.trim();
    }

    public String getApproveScore() {
        return approveScore;
    }

    public void setApproveScore(String approveScore) {
        this.approveScore = approveScore == null ? null : approveScore.trim();
    }

    public String getCruxplanScore() {
        return cruxplanScore;
    }

    public void setCruxplanScore(String cruxplanScore) {
        this.cruxplanScore = cruxplanScore == null ? null : cruxplanScore.trim();
    }

    public String getTechnologyScore() {
        return technologyScore;
    }

    public void setTechnologyScore(String technologyScore) {
        this.technologyScore = technologyScore == null ? null : technologyScore.trim();
    }

    public String getEfficiencyScore() {
        return efficiencyScore;
    }

    public void setEfficiencyScore(String efficiencyScore) {
        this.efficiencyScore = efficiencyScore == null ? null : efficiencyScore.trim();
    }

    public String getInnovatestudyScore() {
        return innovatestudyScore;
    }

    public void setInnovatestudyScore(String innovatestudyScore) {
        this.innovatestudyScore = innovatestudyScore == null ? null : innovatestudyScore.trim();
    }

    public String getTeamworkScore() {
        return teamworkScore;
    }

    public void setTeamworkScore(String teamworkScore) {
        this.teamworkScore = teamworkScore == null ? null : teamworkScore.trim();
    }

    public String getCommunicationScore() {
        return communicationScore;
    }

    public void setCommunicationScore(String communicationScore) {
        this.communicationScore = communicationScore == null ? null : communicationScore.trim();
    }
}