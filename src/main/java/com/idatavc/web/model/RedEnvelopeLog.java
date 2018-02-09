package com.idatavc.web.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "red_envelope_log")
public class RedEnvelopeLog {
    /**
     * 红包ID
     */
    @Id
    @Column(name = "red_envelope_id")
    private Long redEnvelopeId;

    /**
     * 领取人token
     */
    @Id
    private String token;

    /**
     * 领取时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 领取金额
     */
    private BigDecimal amount;

    @Column(name = "app_id")
    private Integer appId;

    /**
     * 被领取人
     */
    @Column(name = "from_token")
    private String fromToken;

    /**
     * 获取红包ID
     *
     * @return red_envelope_id - 红包ID
     */
    public Long getRedEnvelopeId() {
        return redEnvelopeId;
    }

    /**
     * 设置红包ID
     *
     * @param redEnvelopeId 红包ID
     */
    public void setRedEnvelopeId(Long redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }

    /**
     * 获取领取人token
     *
     * @return token - 领取人token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置领取人token
     *
     * @param token 领取人token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取领取时间
     *
     * @return create_time - 领取时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置领取时间
     *
     * @param createTime 领取时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取领取金额
     *
     * @return amount - 领取金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置领取金额
     *
     * @param amount 领取金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return app_id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取被领取人
     *
     * @return from_token - 被领取人
     */
    public String getFromToken() {
        return fromToken;
    }

    /**
     * 设置被领取人
     *
     * @param fromToken 被领取人
     */
    public void setFromToken(String fromToken) {
        this.fromToken = fromToken;
    }
}