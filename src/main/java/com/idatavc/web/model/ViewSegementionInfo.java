package com.idatavc.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "view_segemention_info")
public class ViewSegementionInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目编号
     */
    @Column(name = "serial_number")
    private Integer serialNumber;

    /**
     * 项目简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 工商注册全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 一句话介绍
     */
    @Column(name = "kernel_desc")
    private String kernelDesc;

    /**
     * 官网url
     */
    private String url;

    @Column(name = "established_time")
    private Date establishedTime;

    /**
     * 轮次
     */
    private String stage;

    /**
     * 领域名称
     */
    private String segmentation;

    /**
     * 项目标签
     */
    @Column(name = "item_label")
    private String itemLabel;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 地域
     */
    private String territory;

    /**
     * 公司成立时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 洲
     */
    private String continent;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String region;

    /**
     * 机构简称
     */
    @Column(name = "investment_institutions_short_name")
    private String investmentInstitutionsShortName;

    /**
     * 机构备注
     */
    @Column(name = "investment_institutions_commet")
    private String investmentInstitutionsCommet;

    /**
     * 机构类型：1: 50机构，0: 非50机构
     */
    @Column(name = "investment_institutions_type")
    private Integer investmentInstitutionsType;

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
     * 获取项目编号
     *
     * @return serial_number - 项目编号
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置项目编号
     *
     * @param serialNumber 项目编号
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取项目简称
     *
     * @return short_name - 项目简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置项目简称
     *
     * @param shortName 项目简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取工商注册全称
     *
     * @return full_name - 工商注册全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置工商注册全称
     *
     * @param fullName 工商注册全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取一句话介绍
     *
     * @return kernel_desc - 一句话介绍
     */
    public String getKernelDesc() {
        return kernelDesc;
    }

    /**
     * 设置一句话介绍
     *
     * @param kernelDesc 一句话介绍
     */
    public void setKernelDesc(String kernelDesc) {
        this.kernelDesc = kernelDesc;
    }

    /**
     * 获取官网url
     *
     * @return url - 官网url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置官网url
     *
     * @param url 官网url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return established_time
     */
    public Date getEstablishedTime() {
        return establishedTime;
    }

    /**
     * @param establishedTime
     */
    public void setEstablishedTime(Date establishedTime) {
        this.establishedTime = establishedTime;
    }

    /**
     * 获取轮次
     *
     * @return stage - 轮次
     */
    public String getStage() {
        return stage;
    }

    /**
     * 设置轮次
     *
     * @param stage 轮次
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * 获取领域名称
     *
     * @return segmentation - 领域名称
     */
    public String getSegmentation() {
        return segmentation;
    }

    /**
     * 设置领域名称
     *
     * @param segmentation 领域名称
     */
    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    /**
     * 获取项目标签
     *
     * @return item_label - 项目标签
     */
    public String getItemLabel() {
        return itemLabel;
    }

    /**
     * 设置项目标签
     *
     * @param itemLabel 项目标签
     */
    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    /**
     * 获取公司地址
     *
     * @return address - 公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置公司地址
     *
     * @param address 公司地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取地域
     *
     * @return territory - 地域
     */
    public String getTerritory() {
        return territory;
    }

    /**
     * 设置地域
     *
     * @param territory 地域
     */
    public void setTerritory(String territory) {
        this.territory = territory;
    }

    /**
     * 获取公司成立时间
     *
     * @return create_time - 公司成立时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置公司成立时间
     *
     * @param createTime 公司成立时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取洲
     *
     * @return continent - 洲
     */
    public String getContinent() {
        return continent;
    }

    /**
     * 设置洲
     *
     * @param continent 洲
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区县
     *
     * @return region - 区县
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置区县
     *
     * @param region 区县
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 获取机构简称
     *
     * @return investment_institutions_short_name - 机构简称
     */
    public String getInvestmentInstitutionsShortName() {
        return investmentInstitutionsShortName;
    }

    /**
     * 设置机构简称
     *
     * @param investmentInstitutionsShortName 机构简称
     */
    public void setInvestmentInstitutionsShortName(String investmentInstitutionsShortName) {
        this.investmentInstitutionsShortName = investmentInstitutionsShortName;
    }

    /**
     * 获取机构备注
     *
     * @return investment_institutions_commet - 机构备注
     */
    public String getInvestmentInstitutionsCommet() {
        return investmentInstitutionsCommet;
    }

    /**
     * 设置机构备注
     *
     * @param investmentInstitutionsCommet 机构备注
     */
    public void setInvestmentInstitutionsCommet(String investmentInstitutionsCommet) {
        this.investmentInstitutionsCommet = investmentInstitutionsCommet;
    }

    /**
     * 获取机构类型：1: 50机构，0: 非50机构
     *
     * @return investment_institutions_type - 机构类型：1: 50机构，0: 非50机构
     */
    public Integer getInvestmentInstitutionsType() {
        return investmentInstitutionsType;
    }

    /**
     * 设置机构类型：1: 50机构，0: 非50机构
     *
     * @param investmentInstitutionsType 机构类型：1: 50机构，0: 非50机构
     */
    public void setInvestmentInstitutionsType(Integer investmentInstitutionsType) {
        this.investmentInstitutionsType = investmentInstitutionsType;
    }
}