package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "meta_segmentation")
public class MetaSegmentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 领域logo
     */
    @Column(name = "segmentation_logo")
    private String segmentationLogo;

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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取领域logo
     *
     * @return segmentation_logo - 领域logo
     */
    public String getSegmentationLogo() {
        return segmentationLogo;
    }

    /**
     * 设置领域logo
     *
     * @param segmentationLogo 领域logo
     */
    public void setSegmentationLogo(String segmentationLogo) {
        this.segmentationLogo = segmentationLogo;
    }
}