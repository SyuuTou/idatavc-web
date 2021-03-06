package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "project_segmentation")
public class ProjectSegmentation {
    /**
     * 项目iD
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 领域名称
     */
    @Column(name = "segmentation_name")
    private String segmentationName;

    private Integer count;

    /**
     * 获取项目iD
     *
     * @return project_id - 项目iD
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目iD
     *
     * @param projectId 项目iD
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取领域名称
     *
     * @return segmentation_name - 领域名称
     */
    public String getSegmentationName() {
        return segmentationName;
    }

    /**
     * 设置领域名称
     *
     * @param segmentationName 领域名称
     */
    public void setSegmentationName(String segmentationName) {
        this.segmentationName = segmentationName;
    }

    /**
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}