package com.yizhanshi.course.api.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.annotation.Excel.ColumnType;
import com.yizhanshi.common.core.annotation.Excels;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.place.api.domain.Place;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Course extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "课程序号", cellType = ColumnType.NUMERIC)
    private Long courseId;
    @Excel(name = "课程名称")
    private  String courseName;
    @Excel(name = "课程类型")
    private String courseType;
    @Excel(name = "课程描述")
    private  String courseDescription;
    @Excel(name = "老师序号", cellType = ColumnType.NUMERIC)
    private Long teacherId;
    @Excel(name = "课程日期",dateFormat = "yyyy-MM-dd")
    private Date courseDay;
    private Long timeStartId;
    private Long timeEndId;
    @Excel(name = "课程开始时间")
    private String courseStartTime;
    @Excel(name = "课程结束时间")
    private String courseEndTime;
    @Excel(name = "场地序号", cellType = ColumnType.NUMERIC)
    private Long placeId;
    @Excel(name = "课程容量", cellType = ColumnType.NUMERIC)
    private int courseNumber;
    @Excel(name = "选课状态",readConverterExp = "0=多人选,1=仅限单人")
    private  String chooseStatus;
    @Excel(name = "审核类型",readConverterExp = "0=自动通过,1=一级管理员审核,2=二级管理员审核" )
    private String courseCheck;
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    private String delFlag;

    private Teacher teachers;

    private Place places;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseCheck() {
        return courseCheck;
    }

    public void setCourseCheck(String courseCheck) {
        this.courseCheck = courseCheck;
    }

    public String getChooseStatus() {
        return chooseStatus;
    }

    public void setChooseStatus(String chooseStatus) {
        this.chooseStatus = chooseStatus;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
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

    public Teacher getTeachers() {
        return teachers;
    }

    public void setTeachers(Teacher teachers) {
        this.teachers = teachers;
    }

    public Place getPlaces() {
        return places;
    }

    public void setPlaces(Place places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("courseId", courseId)
                .append("courseName", courseName)
                .append("courseDescription", courseDescription)
                .append("courseType", courseType)
                .append("teacherId", teacherId)
                .append("courseDay", courseDay)
                .append("timeStartId", timeStartId)
                .append("timeEndId", timeEndId)
                .append("courseStartTime", courseStartTime)
                .append("courseEndTime", courseEndTime)
                .append("placeId", placeId)
                .append("courseNumber", courseNumber)
                .append("chooseStatus", chooseStatus)
                .append("courseCheck", courseCheck)
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
