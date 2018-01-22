package com.idatavc.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "admin_operation_log")
public class AdminOperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 操作功能模块名称
     */
    private String name;

    /**
     * 操作类型：add, update, delete
     */
    private String type;

    /**
     * 操作前的数据，添加数据时为 null，更新和删除时要记录更新和删除前的数据
     */
    @Column(name = "operation_before")
    private String operationBefore;

    /**
     * 操作后的数据，添加和更新后的数据，删除时为null
     */
    @Column(name = "operation_after")
    private String operationAfter;

    /**
     * 操作人token
     */
    private String creater;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取操作功能模块名称
     *
     * @return name - 操作功能模块名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置操作功能模块名称
     *
     * @param name 操作功能模块名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取操作类型：add, update, delete
     *
     * @return type - 操作类型：add, update, delete
     */
    public String getType() {
        return type;
    }

    /**
     * 设置操作类型：add, update, delete
     *
     * @param type 操作类型：add, update, delete
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取操作前的数据，添加数据时为 null，更新和删除时要记录更新和删除前的数据
     *
     * @return operation_before - 操作前的数据，添加数据时为 null，更新和删除时要记录更新和删除前的数据
     */
    public String getOperationBefore() {
        return operationBefore;
    }

    /**
     * 设置操作前的数据，添加数据时为 null，更新和删除时要记录更新和删除前的数据
     *
     * @param operationBefore 操作前的数据，添加数据时为 null，更新和删除时要记录更新和删除前的数据
     */
    public void setOperationBefore(String operationBefore) {
        this.operationBefore = operationBefore;
    }

    /**
     * 获取操作后的数据，添加和更新后的数据，删除时为null
     *
     * @return operation_after - 操作后的数据，添加和更新后的数据，删除时为null
     */
    public String getOperationAfter() {
        return operationAfter;
    }

    /**
     * 设置操作后的数据，添加和更新后的数据，删除时为null
     *
     * @param operationAfter 操作后的数据，添加和更新后的数据，删除时为null
     */
    public void setOperationAfter(String operationAfter) {
        this.operationAfter = operationAfter;
    }

    /**
     * 获取操作人token
     *
     * @return creater - 操作人token
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置操作人token
     *
     * @param creater 操作人token
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}