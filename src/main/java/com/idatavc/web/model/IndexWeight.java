package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "index_weight")
public class IndexWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权重值
     */
    private Integer value;

    /**
     * 权重名称
     */
    private String name;

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
     * 获取权重值
     *
     * @return value - 权重值
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 设置权重值
     *
     * @param value 权重值
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 获取权重名称
     *
     * @return name - 权重名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权重名称
     *
     * @param name 权重名称
     */
    public void setName(String name) {
        this.name = name;
    }
}