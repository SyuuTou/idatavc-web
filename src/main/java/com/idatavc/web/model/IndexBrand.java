package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "index_brand")
public class IndexBrand {
    @Id
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 荣誉指数
     */
    private Integer honor;

    /**
     * 百度指数
     */
    @Column(name = "baidu_index")
    private Integer baiduIndex;

    /**
     * 腾讯指数
     */
    @Column(name = "tencent_index")
    private Integer tencentIndex;

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
     * 获取荣誉指数
     *
     * @return honor - 荣誉指数
     */
    public Integer getHonor() {
        return honor;
    }

    /**
     * 设置荣誉指数
     *
     * @param honor 荣誉指数
     */
    public void setHonor(Integer honor) {
        this.honor = honor;
    }

    /**
     * 获取百度指数
     *
     * @return baidu_index - 百度指数
     */
    public Integer getBaiduIndex() {
        return baiduIndex;
    }

    /**
     * 设置百度指数
     *
     * @param baiduIndex 百度指数
     */
    public void setBaiduIndex(Integer baiduIndex) {
        this.baiduIndex = baiduIndex;
    }

    /**
     * 获取腾讯指数
     *
     * @return tencent_index - 腾讯指数
     */
    public Integer getTencentIndex() {
        return tencentIndex;
    }

    /**
     * 设置腾讯指数
     *
     * @param tencentIndex 腾讯指数
     */
    public void setTencentIndex(Integer tencentIndex) {
        this.tencentIndex = tencentIndex;
    }
}