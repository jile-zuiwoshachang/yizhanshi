package com.yizhanshi.course.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.place.api.domain.Place;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CourseTime extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "课程时间序号", cellType = Excel.ColumnType.NUMERIC)
    private Long courseTimeId;
    @Excel(name = "课程日期",dateFormat = "yyyy-MM-dd")
    private Date courseDay;
    private Long timeStartId;
    private Long timeEndId;
    @Excel(name = "课程开始时间")
    private String courseStartTime;
    @Excel(name = "课程结束时间")
    private String courseEndTime;
    @Excel(name = "场地序号", cellType = Excel.ColumnType.NUMERIC)
    private Long placeId;
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    private String delFlag;
    private Place place;
    /**
     * 前端传参用
     */
    private Long courseId;
    /**
     * 返回前端用
     */
    private Course course;
    public CourseTime(){}
    public CourseTime(Long courseTimeId, Date courseDay, Long timeStartId, Long timeEndId, String courseStartTime, String courseEndTime, Long placeId, String status, String delFlag) {
        this.courseTimeId = courseTimeId;
        this.courseDay = courseDay;
        this.timeStartId = timeStartId;
        this.timeEndId = timeEndId;
        this.courseStartTime = courseStartTime;
        this.courseEndTime = courseEndTime;
        this.placeId = placeId;
        this.status = status;
        this.delFlag = delFlag;
    }

    @NotNull(message = "课程日期不可为null")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getCourseDay() {
        return courseDay;
    }

    public void setCourseDay(Date courseDay) {
        this.courseDay = courseDay;
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
    @NotNull(message = "上课教室编号不可为null")
    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getCourseTimeId() {
        return courseTimeId;
    }

    public void setCourseTimeId(Long courseTimeId) {
        this.courseTimeId = courseTimeId;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("courseTimeId", courseTimeId)
                .append("courseDay", courseDay)
                .append("timeStartId", timeStartId)
                .append("timeEndId", timeEndId)
                .append("courseStartTime", courseStartTime)
                .append("courseEndTime", courseEndTime)
                .append("placeId", placeId)
                .append("status", status)
                .append("delFlag", delFlag)
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
