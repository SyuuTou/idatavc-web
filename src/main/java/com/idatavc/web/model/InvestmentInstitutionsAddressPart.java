package com.idatavc.web.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "investment_institutions_address_part")
public class InvestmentInstitutionsAddressPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构id
     */
    @Column(name = "investment_institution_id")
    private Integer investmentInstitutionId;

    /**
     * 分部名称
     */
    private String name;

    /**
     * 所在城市
     */
    private String town;

    /**
     * 详细地址
     */
    @Column(name = "detail_address")
    private String detailAddress;

    /**
     * 电话国家码
     */
    @Column(name = "phone_country_code")
    private String phoneCountryCode;

    /**
     * 电话区号
     */
    @Column(name = "phone_district_code")
    private String phoneDistrictCode;

    /**
     * 电话号（不包含，国家码，区号）
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    private BigDecimal longitude;

    private BigDecimal latitude;

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
     * 获取机构id
     *
     * @return investment_institution_id - 机构id
     */
    public Integer getInvestmentInstitutionId() {
        return investmentInstitutionId;
    }

    /**
     * 设置机构id
     *
     * @param investmentInstitutionId 机构id
     */
    public void setInvestmentInstitutionId(Integer investmentInstitutionId) {
        this.investmentInstitutionId = investmentInstitutionId;
    }

    /**
     * 获取分部名称
     *
     * @return name - 分部名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分部名称
     *
     * @param name 分部名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取所在城市
     *
     * @return town - 所在城市
     */
    public String getTown() {
        return town;
    }

    /**
     * 设置所在城市
     *
     * @param town 所在城市
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * 获取详细地址
     *
     * @return detail_address - 详细地址
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * 设置详细地址
     *
     * @param detailAddress 详细地址
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    /**
     * 获取电话国家码
     *
     * @return phone_country_code - 电话国家码
     */
    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    /**
     * 设置电话国家码
     *
     * @param phoneCountryCode 电话国家码
     */
    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    /**
     * 获取电话区号
     *
     * @return phone_district_code - 电话区号
     */
    public String getPhoneDistrictCode() {
        return phoneDistrictCode;
    }

    /**
     * 设置电话区号
     *
     * @param phoneDistrictCode 电话区号
     */
    public void setPhoneDistrictCode(String phoneDistrictCode) {
        this.phoneDistrictCode = phoneDistrictCode;
    }

    /**
     * 获取电话号（不包含，国家码，区号）
     *
     * @return phone_number - 电话号（不包含，国家码，区号）
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置电话号（不包含，国家码，区号）
     *
     * @param phoneNumber 电话号（不包含，国家码，区号）
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * @return latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}