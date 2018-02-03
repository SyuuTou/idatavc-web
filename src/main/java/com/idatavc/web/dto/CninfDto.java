package com.idatavc.web.dto;

import java.util.List;

public class CninfDto {

    private String classifiedAnnouncements;
    private Long totalSecurities;
    private Long totalAnnouncement;
    private Long totalRecordNum;
    private List<Announcement> announcements;

    public String getClassifiedAnnouncements() {
        return classifiedAnnouncements;
    }

    public void setClassifiedAnnouncements(String classifiedAnnouncements) {
        this.classifiedAnnouncements = classifiedAnnouncements;
    }

    public Long getTotalSecurities() {
        return totalSecurities;
    }

    public void setTotalSecurities(Long totalSecurities) {
        this.totalSecurities = totalSecurities;
    }

    public Long getTotalAnnouncement() {
        return totalAnnouncement;
    }

    public void setTotalAnnouncement(Long totalAnnouncement) {
        this.totalAnnouncement = totalAnnouncement;
    }

    public Long getTotalRecordNum() {
        return totalRecordNum;
    }

    public void setTotalRecordNum(Long totalRecordNum) {
        this.totalRecordNum = totalRecordNum;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }
}
