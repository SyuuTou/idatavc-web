package com.idatavc.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_operation_log")
public class UserOperationLog {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户Token
     */
    private String token;

    /**
     * 操作内容
     */
    private String operations;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return ID
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
     * 获取用户Token
     *
     * @return token - 用户Token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置用户Token
     *
     * @param token 用户Token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取操作内容
     *
     * @return operations - 操作内容
     */
    public String getOperations() {
        return operations;
    }

    /**
     * 设置操作内容
     *
     * @param operations 操作内容
     */
    public void setOperations(String operations) {
        this.operations = operations;
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