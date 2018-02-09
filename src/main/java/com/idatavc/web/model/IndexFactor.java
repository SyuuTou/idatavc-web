package com.idatavc.web.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "index_factor")
public class IndexFactor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    private BigDecimal city;

    private BigDecimal seg;

    private BigDecimal jigou;

    private BigDecimal edus;

    private BigDecimal work;

    @Column(name = "city_count")
    private Integer cityCount;

    @Column(name = "seg_count")
    private Integer segCount;

    @Column(name = "jigou_count")
    private Integer jigouCount;

    @Column(name = "edus_count")
    private Integer edusCount;

    @Column(name = "work_count")
    private Integer workCount;

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
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return city
     */
    public BigDecimal getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(BigDecimal city) {
        this.city = city;
    }

    /**
     * @return seg
     */
    public BigDecimal getSeg() {
        return seg;
    }

    /**
     * @param seg
     */
    public void setSeg(BigDecimal seg) {
        this.seg = seg;
    }

    /**
     * @return jigou
     */
    public BigDecimal getJigou() {
        return jigou;
    }

    /**
     * @param jigou
     */
    public void setJigou(BigDecimal jigou) {
        this.jigou = jigou;
    }

    /**
     * @return edus
     */
    public BigDecimal getEdus() {
        return edus;
    }

    /**
     * @param edus
     */
    public void setEdus(BigDecimal edus) {
        this.edus = edus;
    }

    /**
     * @return work
     */
    public BigDecimal getWork() {
        return work;
    }

    /**
     * @param work
     */
    public void setWork(BigDecimal work) {
        this.work = work;
    }

    /**
     * @return city_count
     */
    public Integer getCityCount() {
        return cityCount;
    }

    /**
     * @param cityCount
     */
    public void setCityCount(Integer cityCount) {
        this.cityCount = cityCount;
    }

    /**
     * @return seg_count
     */
    public Integer getSegCount() {
        return segCount;
    }

    /**
     * @param segCount
     */
    public void setSegCount(Integer segCount) {
        this.segCount = segCount;
    }

    /**
     * @return jigou_count
     */
    public Integer getJigouCount() {
        return jigouCount;
    }

    /**
     * @param jigouCount
     */
    public void setJigouCount(Integer jigouCount) {
        this.jigouCount = jigouCount;
    }

    /**
     * @return edus_count
     */
    public Integer getEdusCount() {
        return edusCount;
    }

    /**
     * @param edusCount
     */
    public void setEdusCount(Integer edusCount) {
        this.edusCount = edusCount;
    }

    /**
     * @return work_count
     */
    public Integer getWorkCount() {
        return workCount;
    }

    /**
     * @param workCount
     */
    public void setWorkCount(Integer workCount) {
        this.workCount = workCount;
    }
}