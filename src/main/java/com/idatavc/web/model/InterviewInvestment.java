package com.idatavc.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "interview_investment")
public class InterviewInvestment {
    /**
     * 机构记录id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构id
     */
    @Column(name = "investment_id")
    private Integer investmentId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 约谈内容
     */
    private String dc;

    /**
     * 约谈时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 状态：0：未读，1已读
     */
    private Integer yn;

    /**
     * 获取机构记录id
     *
     * @return ID - 机构记录id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置机构记录id
     *
     * @param id 机构记录id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 获取约谈内容
     *
     * @return dc - 约谈内容
     */
    public String getDc() {
        return dc;
    }

    /**
     * 设置约谈内容
     *
     * @param dc 约谈内容
     */
    public void setDc(String dc) {
        this.dc = dc;
    }

    /**
     * 获取约谈时间
     *
     * @return create_time - 约谈时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置约谈时间
     *
     * @param createTime 约谈时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取状态：0：未读，1已读
     *
     * @return yn - 状态：0：未读，1已读
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置状态：0：未读，1已读
     *
     * @param yn 状态：0：未读，1已读
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}