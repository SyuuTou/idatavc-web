package com.idatavc.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "follow_investment")
public class FollowInvestment {
    /**
     * 记录id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 机构id
     */
    @Column(name = "investment_id")
    private Integer investmentId;

    /**
     * 状态：0：未关注1：关注
     */
    private Integer yn;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取记录id
     *
     * @return ID - 记录id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置记录id
     *
     * @param id 记录id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取机构id
     *
     * @return investment_id - 机构id
     */
    public Integer getInvestmentId() {
        return investmentId;
    }

    /**
     * 设置机构id
     *
     * @param investmentId 机构id
     */
    public void setInvestmentId(Integer investmentId) {
        this.investmentId = investmentId;
    }

    /**
     * 获取状态：0：未关注1：关注
     *
     * @return yn - 状态：0：未关注1：关注
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置状态：0：未关注1：关注
     *
     * @param yn 状态：0：未关注1：关注
     */
    public void setYn(Integer yn) {
        this.yn = yn;
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