package com.idatavc.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_integral_consume")
public class UserIntegralConsume {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 场景ID
     */
    @Column(name = "scene_id")
    private Integer sceneId;

    /**
     * 花费积分数量
     */
    @Column(name = "cost_num")
    private Integer costNum;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取场景ID
     *
     * @return scene_id - 场景ID
     */
    public Integer getSceneId() {
        return sceneId;
    }

    /**
     * 设置场景ID
     *
     * @param sceneId 场景ID
     */
    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * 获取花费积分数量
     *
     * @return cost_num - 花费积分数量
     */
    public Integer getCostNum() {
        return costNum;
    }

    /**
     * 设置花费积分数量
     *
     * @param costNum 花费积分数量
     */
    public void setCostNum(Integer costNum) {
        this.costNum = costNum;
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

    /**
     * 获取开始时间
     *
     * @return begin_time - 开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置开始时间
     *
     * @param beginTime 开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}