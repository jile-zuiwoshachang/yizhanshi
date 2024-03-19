package com.yizhanshi.place.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 预约场地时间表
 */
public class PlaceApplyTime extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 预约时间序号 */
    @Excel(name = "预约时间序号", cellType = Excel.ColumnType.NUMERIC)
    private Long applyTimeId;
    @Excel(name = "场地id", cellType = Excel.ColumnType.NUMERIC)
    private Long placeId;
    @Excel(name="预约日期",dateFormat = "yyyy-MM-dd")
    private Date applyDay;
    private Long timeStartId;
    private Long timeEndId;

    @Excel(name = "开始时间")
    private String applyStartTime;
    @Excel(name = "结束时间")
    private String applyEndTime;
    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
    private Place place;
    /**
     * 仅用于前端传参
     */
    private Long applyId;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getApplyTimeId() {
        return applyTimeId;
    }

    public void setApplyTimeId(Long applyTimeId) {
        this.applyTimeId = applyTimeId;
    }
    @NotNull(message = "预约教室编号不可为null")
    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
    /**
     * 格式转化为2024-02-01
     * @return
     */
    @NotNull(message = "预约日期不可为null")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(Date applyDay) {
        this.applyDay = applyDay;
    }

    public Long getTimeStartId() {
        return timeStartId;
    }

    public void setTimeStartId(Long timeStartId) {
        this.timeStartId = timeStartId;
    }

    public Long getTimeEndId() {
        return timeEndId;
    }

    public void setTimeEndId(Long timeEndId) {
        this.timeEndId = timeEndId;
    }

    public String getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(String applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public String getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(String applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("applyTimeId", applyTimeId)
                .append("placeId", placeId)
                .append("applyDay", applyDay)
                .append("timeStartId", timeStartId)
                .append("timeEndId", timeEndId)
                .append("applyStartTime", applyStartTime)
                .append("applyEndTime", applyEndTime)
                .append("status", status)
                .append("delFlag", delFlag)
                .append("place", place)
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
