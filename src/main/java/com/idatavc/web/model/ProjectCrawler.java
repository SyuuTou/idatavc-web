package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "project_crawler")
public class ProjectCrawler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "financing_time")
    private String financingTime;

    private String stage;

    @Column(name = "now_stage")
    private String nowStage;

    private String segmentation;

    @Column(name = "kenel_words")
    private String kenelWords;

    private String logo;

    private String city;

    @Column(name = "new_logo")
    private String newLogo;

    @Column(name = "amount_desc")
    private String amountDesc;

    private String investors;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return short_name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return financing_time
     */
    public String getFinancingTime() {
        return financingTime;
    }

    /**
     * @param financingTime
     */
    public void setFinancingTime(String financingTime) {
        this.financingTime = financingTime;
    }

    /**
     * @return stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * @return now_stage
     */
    public String getNowStage() {
        return nowStage;
    }

    /**
     * @param nowStage
     */
    public void setNowStage(String nowStage) {
        this.nowStage = nowStage;
    }

    /**
     * @return segmentation
     */
    public String getSegmentation() {
        return segmentation;
    }

    /**
     * @param segmentation
     */
    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    /**
     * @return kenel_words
     */
    public String getKenelWords() {
        return kenelWords;
    }

    /**
     * @param kenelWords
     */
    public void setKenelWords(String kenelWords) {
        this.kenelWords = kenelWords;
    }

    /**
     * @return logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return new_logo
     */
    public String getNewLogo() {
        return newLogo;
    }

    /**
     * @param newLogo
     */
    public void setNewLogo(String newLogo) {
        this.newLogo = newLogo;
    }

    /**
     * @return amount_desc
     */
    public String getAmountDesc() {
        return amountDesc;
    }

    /**
     * @param amountDesc
     */
    public void setAmountDesc(String amountDesc) {
        this.amountDesc = amountDesc;
    }

    /**
     * @return investors
     */
    public String getInvestors() {
        return investors;
    }

    /**
     * @param investors
     */
    public void setInvestors(String investors) {
        this.investors = investors;
    }
}