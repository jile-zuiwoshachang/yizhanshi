package com.yizhanshi.course.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.common.core.annotation.Excel;

import java.util.Date;

/**
 * 导出所有课程
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CourseExport {
    private static final long serialVersionUID = 1L;
    @Excel(name = "课程序号", cellType = Excel.ColumnType.NUMERIC)
    private Long courseId;
    @Excel(name = "课程名称")
    private  String courseName;
    @Excel(name = "课程类型")
    private String courseType;
    @Excel(name = "课程描述")
    private  String courseDescription;
    @Excel(name = "老师名称")
    private String teacherName;
    @Excel(name = "课程日期",dateFormat = "YYYY-MM-dd")
    private Date courseDay;
    @Excel(name = "课程开始时间")
    private String courseStartTime;
    @Excel(name = "课程结束时间")
    private String courseEndTime;
    @Excel(name = "场地名称")
    private String placeName;
    @Excel(name = "场地校区")
    private String placeCampus;
    @Excel(name = "课程容量", cellType = Excel.ColumnType.NUMERIC)
    private int courseNumber;
    @Excel(name = "选课状态",readConverterExp = "0=独立,1=不独立")
    private  String chooseStatus;
    @Excel(name = "审核类型",readConverterExp = "0=自动通过,1=一级管理员审核, 2=二级管理员审核" )
    private String courseCheck;
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public CourseExport() {
    }

    public CourseExport(Long courseId, String courseName, String courseType, String courseDescription, String teacherName, Date courseDay, String courseStartTime, String courseEndTime, String placeName, String placeCampus, int courseNumber, String chooseStatus, String courseCheck, String status, Date createTime) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseDescription = courseDescription;
        this.teacherName = teacherName;
        this.courseDay = courseDay;
        this.courseStartTime = courseStartTime;
        this.courseEndTime = courseEndTime;
        this.placeName = placeName;
        this.placeCampus = placeCampus;
        this.courseNumber = courseNumber;
        this.chooseStatus = chooseStatus;
        this.courseCheck = courseCheck;
        this.status = status;
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getChooseStatus() {
        return chooseStatus;
    }

    public void setChooseStatus(String chooseStatus) {
        this.chooseStatus = chooseStatus;
    }

    public String getCourseCheck() {
        return courseCheck;
    }

    public void setCourseCheck(String courseCheck) {
        this.courseCheck = courseCheck;
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
}
