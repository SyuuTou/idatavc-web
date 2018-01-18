package com.idatavc.web.dto;

public class ProjectDto {
    private Integer id;
    private String  financingTime;
    private String  stage;
    private String  shortName;
    private String  segmentation;
    private String  kenelWords;
    private String  nowStage;
    private String city;
    private String logo;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFinancingTime() {
        return financingTime;
    }

    public void setFinancingTime(String financingTime) {
        this.financingTime = financingTime;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    public String getKenelWords() {
        return kenelWords;
    }

    public void setKenelWords(String kenelWords) {
        this.kenelWords = kenelWords;
    }

    public String getNowStage() {
        return nowStage;
    }

    public void setNowStage(String nowStage) {
        this.nowStage = nowStage;
    }
}
