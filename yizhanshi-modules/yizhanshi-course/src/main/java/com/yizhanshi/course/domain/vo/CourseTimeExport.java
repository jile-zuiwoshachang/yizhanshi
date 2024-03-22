package com.yizhanshi.course.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.common.core.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class CourseTimeExport {
    private static final long serialVersionUID = 1L;
    @Excel(name = "课程时间序号", cellType = Excel.ColumnType.NUMERIC)
    private Long courseTimeId;
    @Excel(name = "课程日期",dateFormat = "yyyy-MM-dd")
    private Date courseDay;
    @Excel(name = "课程开始时间")
    private String courseStartTime;
    @Excel(name = "课程结束时间")
    private String courseEndTime;
    @Excel(name = "场地名称")
    private String  placeName;
    @Excel(name = "场地所在校区")
    private String  placeCampus;
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "创建人")
    private String createBy;
    public CourseTimeExport(){}

    public CourseTimeExport(Long courseTimeId, Date courseDay, String courseStartTime, String courseEndTime, String placeName, String placeCampus, String status, Date createTime, String createBy) {
        this.courseTimeId = courseTimeId;
        this.courseDay = courseDay;
        this.courseStartTime = courseStartTime;
        this.courseEndTime = courseEndTime;
        this.placeName = placeName;
        this.placeCampus = placeCampus;
        this.status = status;
        this.createTime = createTime;
        this.createBy = createBy;
    }

    public Long getCourseTimeId() {
        return courseTimeId;
    }

    public void setCourseTimeId(Long courseTimeId) {
        this.courseTimeId = courseTimeId;
    }

    public Date getCourseDay() {
        return courseDay;
    }

    public void setCourseDay(Date courseDay) {
        this.courseDay = courseDay;
    }

    public String getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseStartTime(String courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public String getCourseEndTime() {
        return courseEndTime;
    }

    public void setCourseEndTime(String courseEndTime) {
        this.courseEndTime = courseEndTime;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("courseTimeId", courseTimeId)
                .append("courseDay", courseDay)
                .append("courseStartTime", courseStartTime)
                .append("courseEndTime", courseEndTime)
                .append("placeName", placeName)
                .append("placeCampus", placeCampus)
                .append("status", status)
                .append("createTime", createTime)
                .append("createBy", createBy)
                .toString();
    }
}
