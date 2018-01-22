package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "index_market")
public class IndexMarket {
    /**
     * 项目指数表ID
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 所属领域指数
     */
    private Integer segmentation;

    /**
     * 细分赛道指数
     */
    @Column(name = "subdivide_segmentation")
    private Integer subdivideSegmentation;

    /**
     * 经营状况
     */
    @Column(name = "operating_conditions")
    private Integer operatingConditions;

    /**
     * 获取项目指数表ID
     *
     * @return project_id - 项目指数表ID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目指数表ID
     *
     * @param projectId 项目指数表ID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取所属领域指数
     *
     * @return segmentation - 所属领域指数
     */
    public Integer getSegmentation() {
        return segmentation;
    }

    /**
     * 设置所属领域指数
     *
     * @param segmentation 所属领域指数
     */
    public void setSegmentation(Integer segmentation) {
        this.segmentation = segmentation;
    }

    /**
     * 获取细分赛道指数
     *
     * @return subdivide_segmentation - 细分赛道指数
     */
    public Integer getSubdivideSegmentation() {
        return subdivideSegmentation;
    }

    /**
     * 设置细分赛道指数
     *
     * @param subdivideSegmentation 细分赛道指数
     */
    public void setSubdivideSegmentation(Integer subdivideSegmentation) {
        this.subdivideSegmentation = subdivideSegmentation;
    }

    /**
     * 获取经营状况
     *
     * @return operating_conditions - 经营状况
     */
    public Integer getOperatingConditions() {
        return operatingConditions;
    }

    /**
     * 设置经营状况
     *
     * @param operatingConditions 经营状况
     */
    public void setOperatingConditions(Integer operatingConditions) {
        this.operatingConditions = operatingConditions;
    }
}