package com.idatavc.web.model;

import javax.persistence.*;

@Table(name = "view_segemention_info")
public class ViewSegementionInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial_number")
    private Integer serialNumber;

    @Column(name = "short_name")
    private Integer shortName;

    @Column(name = "full_name")
    private Integer fullName;

    @Column(name = "kernel_desc")
    private Integer kernelDesc;

    private Integer url;

    @Column(name = "established_time")
    private Integer establishedTime;

    private Integer stage;

    private Integer segmentation;

    @Column(name = "item_label")
    private Integer itemLabel;

    private Integer address;

    private Integer territory;

    @Column(name = "create_time")
    private Integer createTime;

    private Integer continent;

    private Integer country;

    private Integer province;

    private Integer city;

    private Integer region;

    @Column(name = "investment_institutions_short_name")
    private Integer investmentInstitutionsShortName;

    @Column(name = "investment_institutions_commet")
    private Integer investmentInstitutionsCommet;

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
     * @return serial_number
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return short_name
     */
    public Integer getShortName() {
        return shortName;
    }

    /**
     * @param shortName
     */
    public void setShortName(Integer shortName) {
        this.shortName = shortName;
    }

    /**
     * @return full_name
     */
    public Integer getFullName() {
        return fullName;
    }

    /**
     * @param fullName
     */
    public void setFullName(Integer fullName) {
        this.fullName = fullName;
    }

    /**
     * @return kernel_desc
     */
    public Integer getKernelDesc() {
        return kernelDesc;
    }

    /**
     * @param kernelDesc
     */
    public void setKernelDesc(Integer kernelDesc) {
        this.kernelDesc = kernelDesc;
    }

    /**
     * @return url
     */
    public Integer getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(Integer url) {
        this.url = url;
    }

    /**
     * @return established_time
     */
    public Integer getEstablishedTime() {
        return establishedTime;
    }

    /**
     * @param establishedTime
     */
    public void setEstablishedTime(Integer establishedTime) {
        this.establishedTime = establishedTime;
    }

    /**
     * @return stage
     */
    public Integer getStage() {
        return stage;
    }

    /**
     * @param stage
     */
    public void setStage(Integer stage) {
        this.stage = stage;
    }

    /**
     * @return segmentation
     */
    public Integer getSegmentation() {
        return segmentation;
    }

    /**
     * @param segmentation
     */
    public void setSegmentation(Integer segmentation) {
        this.segmentation = segmentation;
    }

    /**
     * @return item_label
     */
    public Integer getItemLabel() {
        return itemLabel;
    }

    /**
     * @param itemLabel
     */
    public void setItemLabel(Integer itemLabel) {
        this.itemLabel = itemLabel;
    }

    /**
     * @return address
     */
    public Integer getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(Integer address) {
        this.address = address;
    }

    /**
     * @return territory
     */
    public Integer getTerritory() {
        return territory;
    }

    /**
     * @param territory
     */
    public void setTerritory(Integer territory) {
        this.territory = territory;
    }

    /**
     * @return create_time
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * @return continent
     */
    public Integer getContinent() {
        return continent;
    }

    /**
     * @param continent
     */
    public void setContinent(Integer continent) {
        this.continent = continent;
    }

    /**
     * @return country
     */
    public Integer getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(Integer country) {
        this.country = country;
    }

    /**
     * @return province
     */
    public Integer getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(Integer province) {
        this.province = province;
    }

    /**
     * @return city
     */
    public Integer getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(Integer city) {
        this.city = city;
    }

    /**
     * @return region
     */
    public Integer getRegion() {
        return region;
    }

    /**
     * @param region
     */
    public void setRegion(Integer region) {
        this.region = region;
    }

    /**
     * @return investment_institutions_short_name
     */
    public Integer getInvestmentInstitutionsShortName() {
        return investmentInstitutionsShortName;
    }

    /**
     * @param investmentInstitutionsShortName
     */
    public void setInvestmentInstitutionsShortName(Integer investmentInstitutionsShortName) {
        this.investmentInstitutionsShortName = investmentInstitutionsShortName;
    }

    /**
     * @return investment_institutions_commet
     */
    public Integer getInvestmentInstitutionsCommet() {
        return investmentInstitutionsCommet;
    }

    /**
     * @param investmentInstitutionsCommet
     */
    public void setInvestmentInstitutionsCommet(Integer investmentInstitutionsCommet) {
        this.investmentInstitutionsCommet = investmentInstitutionsCommet;
    }

    /**
     * @return investment_institutions_type
     */
    public Integer getInvestmentInstitutionsType() {
        return investmentInstitutionsType;
    }

    /**
     * @param investmentInstitutionsType
     */
    public void setInvestmentInstitutionsType(Integer investmentInstitutionsType) {
        this.investmentInstitutionsType = investmentInstitutionsType;
    }
}