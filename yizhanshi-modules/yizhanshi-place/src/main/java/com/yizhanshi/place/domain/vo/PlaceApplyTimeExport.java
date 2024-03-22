package com.yizhanshi.place.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.place.api.domain.Place;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class PlaceApplyTimeExport {
    private static final long serialVersionUID = 1L;
    @Excel(name = "预约时间序号", cellType = Excel.ColumnType.NUMERIC)
    private Long applyTimeId;
    @Excel(name = "场地名称")
    private String placeName;
    @Excel(name = "场地所在校区")
    private String placeCampus;
    @Excel(name="预约日期",dateFormat = "yyyy-MM-dd")
    private Date applyDay;

    @Excel(name = "开始时间")
    private String applyStartTime;
    @Excel(name = "结束时间")
    private String applyEndTime;
    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "创建人")
    private String createBy;

    public PlaceApplyTimeExport(Long applyTimeId, String placeName, String placeCampus, Date applyDay, String applyStartTime, String applyEndTime, String status, Date createTime, String createBy) {
        this.applyTimeId = applyTimeId;
        this.placeName = placeName;
        this.placeCampus = placeCampus;
        this.applyDay = applyDay;
        this.applyStartTime = applyStartTime;
        this.applyEndTime = applyEndTime;
        this.status = status;
        this.createTime = createTime;
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public PlaceApplyTimeExport() {

    }

    public Long getApplyTimeId() {
        return applyTimeId;
    }

    public void setApplyTimeId(Long applyTimeId) {
        this.applyTimeId = applyTimeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceCampus() {
        return placeCampus;
    }

    public void setPlaceCampus(String placeCampus) {
        this.placeCampus = placeCampus;
    }

    public Date getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(Date applyDay) {
        this.applyDay = applyDay;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("applyTimeId", applyTimeId)
                .append("placeName", placeName)
                .append("placeCampus", placeCampus)
                .append("applyDay", applyDay)
                .append("applyStartTime", applyStartTime)
                .append("applyEndTime", applyEndTime)
                .append("status", status)
                .append("createTime", createTime)
                .append("createBy", createBy)
                .toString();
    }
}
