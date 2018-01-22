package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "index_project")
public class IndexProject {
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 市场指数
     */
    private Integer market;

    /**
     * 团队指数
     */
    private Integer team;

    /**
     * 动态指数
     */
    private Integer dynamic;

    /**
     * 资本指数

     */
    private Integer capital;

    /**
     * 品牌指数
     */
    private Integer brand;

    /**
     * @return project_id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取市场指数
     *
     * @return market - 市场指数
     */
    public Integer getMarket() {
        return market;
    }

    /**
     * 设置市场指数
     *
     * @param market 市场指数
     */
    public void setMarket(Integer market) {
        this.market = market;
    }

    /**
     * 获取团队指数
     *
     * @return team - 团队指数
     */
    public Integer getTeam() {
        return team;
    }

    /**
     * 设置团队指数
     *
     * @param team 团队指数
     */
    public void setTeam(Integer team) {
        this.team = team;
    }

    /**
     * 获取动态指数
     *
     * @return dynamic - 动态指数
     */
    public Integer getDynamic() {
        return dynamic;
    }

    /**
     * 设置动态指数
     *
     * @param dynamic 动态指数
     */
    public void setDynamic(Integer dynamic) {
        this.dynamic = dynamic;
    }

    /**
     * 获取资本指数

     *
     * @return capital - 资本指数

     */
    public Integer getCapital() {
        return capital;
    }

    /**
     * 设置资本指数

     *
     * @param capital 资本指数

     */
    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    /**
     * 获取品牌指数
     *
     * @return brand - 品牌指数
     */
    public Integer getBrand() {
        return brand;
    }

    /**
     * 设置品牌指数
     *
     * @param brand 品牌指数
     */
    public void setBrand(Integer brand) {
        this.brand = brand;
    }
}