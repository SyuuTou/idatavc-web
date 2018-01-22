package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "index_dynamic")
public class IndexDynamic {
    @Id
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 关注数量
     */
    private Integer concern;

    /**
     * 约谈数量
     */
    @Column(name = "turn_around")
    private Integer turnAround;

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
     * 获取关注数量
     *
     * @return concern - 关注数量
     */
    public Integer getConcern() {
        return concern;
    }

    /**
     * 设置关注数量
     *
     * @param concern 关注数量
     */
    public void setConcern(Integer concern) {
        this.concern = concern;
    }

    /**
     * 获取约谈数量
     *
     * @return turn_around - 约谈数量
     */
    public Integer getTurnAround() {
        return turnAround;
    }

    /**
     * 设置约谈数量
     *
     * @param turnAround 约谈数量
     */
    public void setTurnAround(Integer turnAround) {
        this.turnAround = turnAround;
    }
}