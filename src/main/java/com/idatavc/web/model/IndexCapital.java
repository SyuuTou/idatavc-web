package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "index_capital")
public class IndexCapital {
    @Id
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 论次
     */
    private Integer stage;

    /**
     * 融资金额
     */
    private Integer amount;

    /**
     * 估值
     */
    private Integer valuation;

    /**
     * 累计获投论次
     */
    @Column(name = "accumulative_stage")
    private Integer accumulativeStage;

    /**
     * 累计获投金额
     */
    @Column(name = "accumulative_amount")
    private Integer accumulativeAmount;

    /**
     * 投资机构背景
     */
    @Column(name = "investment_institutions_backgroud")
    private Integer investmentInstitutionsBackgroud;

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
     * 获取论次
     *
     * @return stage - 论次
     */
    public Integer getStage() {
        return stage;
    }

    /**
     * 设置论次
     *
     * @param stage 论次
     */
    public void setStage(Integer stage) {
        this.stage = stage;
    }

    /**
     * 获取融资金额
     *
     * @return amount - 融资金额
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置融资金额
     *
     * @param amount 融资金额
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取估值
     *
     * @return valuation - 估值
     */
    public Integer getValuation() {
        return valuation;
    }

    /**
     * 设置估值
     *
     * @param valuation 估值
     */
    public void setValuation(Integer valuation) {
        this.valuation = valuation;
    }

    /**
     * 获取累计获投论次
     *
     * @return accumulative_stage - 累计获投论次
     */
    public Integer getAccumulativeStage() {
        return accumulativeStage;
    }

    /**
     * 设置累计获投论次
     *
     * @param accumulativeStage 累计获投论次
     */
    public void setAccumulativeStage(Integer accumulativeStage) {
        this.accumulativeStage = accumulativeStage;
    }

    /**
     * 获取累计获投金额
     *
     * @return accumulative_amount - 累计获投金额
     */
    public Integer getAccumulativeAmount() {
        return accumulativeAmount;
    }

    /**
     * 设置累计获投金额
     *
     * @param accumulativeAmount 累计获投金额
     */
    public void setAccumulativeAmount(Integer accumulativeAmount) {
        this.accumulativeAmount = accumulativeAmount;
    }

    /**
     * 获取投资机构背景
     *
     * @return investment_institutions_backgroud - 投资机构背景
     */
    public Integer getInvestmentInstitutionsBackgroud() {
        return investmentInstitutionsBackgroud;
    }

    /**
     * 设置投资机构背景
     *
     * @param investmentInstitutionsBackgroud 投资机构背景
     */
    public void setInvestmentInstitutionsBackgroud(Integer investmentInstitutionsBackgroud) {
        this.investmentInstitutionsBackgroud = investmentInstitutionsBackgroud;
    }
}