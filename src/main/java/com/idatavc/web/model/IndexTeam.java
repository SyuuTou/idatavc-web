package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "index_team")
public class IndexTeam {
    @Id
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 创始人
     */
    private Integer founder;

    /**
     * 合伙人
     */
    private Integer partner;

    /**
     * 团队成员数量
     */
    private Integer members;

    /**
     * 教育背景
     */
    @Column(name = "educational_background")
    private Integer educationalBackground;

    /**
     * 工作背景
     */
    @Column(name = "work_background")
    private Integer workBackground;

    /**
     * 所在地域
     */
    private Integer territory;

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
     * 获取创始人
     *
     * @return founder - 创始人
     */
    public Integer getFounder() {
        return founder;
    }

    /**
     * 设置创始人
     *
     * @param founder 创始人
     */
    public void setFounder(Integer founder) {
        this.founder = founder;
    }

    /**
     * 获取合伙人
     *
     * @return partner - 合伙人
     */
    public Integer getPartner() {
        return partner;
    }

    /**
     * 设置合伙人
     *
     * @param partner 合伙人
     */
    public void setPartner(Integer partner) {
        this.partner = partner;
    }

    /**
     * 获取团队成员数量
     *
     * @return members - 团队成员数量
     */
    public Integer getMembers() {
        return members;
    }

    /**
     * 设置团队成员数量
     *
     * @param members 团队成员数量
     */
    public void setMembers(Integer members) {
        this.members = members;
    }

    /**
     * 获取教育背景
     *
     * @return educational_background - 教育背景
     */
    public Integer getEducationalBackground() {
        return educationalBackground;
    }

    /**
     * 设置教育背景
     *
     * @param educationalBackground 教育背景
     */
    public void setEducationalBackground(Integer educationalBackground) {
        this.educationalBackground = educationalBackground;
    }

    /**
     * 获取工作背景
     *
     * @return work_background - 工作背景
     */
    public Integer getWorkBackground() {
        return workBackground;
    }

    /**
     * 设置工作背景
     *
     * @param workBackground 工作背景
     */
    public void setWorkBackground(Integer workBackground) {
        this.workBackground = workBackground;
    }

    /**
     * 获取所在地域
     *
     * @return territory - 所在地域
     */
    public Integer getTerritory() {
        return territory;
    }

    /**
     * 设置所在地域
     *
     * @param territory 所在地域
     */
    public void setTerritory(Integer territory) {
        this.territory = territory;
    }
}