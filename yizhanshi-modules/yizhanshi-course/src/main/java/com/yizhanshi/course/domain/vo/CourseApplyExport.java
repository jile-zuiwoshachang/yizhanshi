package com.yizhanshi.course.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.common.core.annotation.Excel;

import java.util.Date;


public class CourseApplyExport {
    private static final long serialVersionUID = 1L;
    @Excel(name = "预约序号", cellType = Excel.ColumnType.NUMERIC)
    private Long applyId;
    @Excel(name = "预约人姓名")
    private String applyName;
    @Excel(name = "预约人学号")
    private String userStudentid;
    @Excel(name = "课程序号", cellType = Excel.ColumnType.NUMERIC)
    private Long courseId;
    @Excel(name = "课程名称")
    private String courseName;
    @Excel(name = "课程类型")
    private String courseType;
    @Excel(name = "预约内容")
    private String applyContent;
    @Excel(name = "一级管理员学号")
    private String applyAdmin1;
    @Excel(name = "一级管理员姓名")
    private String applyAdmin1Name;
    @Excel(name = "二级管理员学号")
    private String applyAdmin2;
    @Excel(name = "二级管理员姓名")
    private String applyAdmin2Name;
    @Excel(name = "拒绝原因")
    private String refuseReason;
    @Excel(name = "撤销状态",readConverterExp = "0=可撤销,1=不可撤销,2=已花费信誉撤销")
    private String recallStatus;
    @Excel(name = "撤销原因")
    private String recallReason;
    @Excel(name = "状态",readConverterExp = "0=已预约,1=一级通过,2=二级通过,4=撤销,5=拒绝")
    private String status;
    @Excel(name = "老师名称")
    private String teacherName;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "创建人")
    private String createBy;
    @Excel(name = "备注")
    private String remark;

    public CourseApplyExport(Long applyId, String applyName, String userStudentid, Long courseId, String courseName, String courseType, String applyContent, String applyAdmin1, String applyAdmin1Name, String applyAdmin2, String applyAdmin2Name, String refuseReason, String recallStatus, String recallReason, String status, String teacherName, Date createTime, String createBy, String remark) {
        this.applyId = applyId;
        this.applyName = applyName;
        this.userStudentid = userStudentid;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseType = courseType;
        this.applyContent = applyContent;
        this.applyAdmin1 = applyAdmin1;
        this.applyAdmin1Name = applyAdmin1Name;
        this.applyAdmin2 = applyAdmin2;
        this.applyAdmin2Name = applyAdmin2Name;
        this.refuseReason = refuseReason;
        this.recallStatus = recallStatus;
        this.recallReason = recallReason;
        this.status = status;
        this.teacherName = teacherName;
        this.createTime = createTime;
        this.createBy = createBy;
        this.remark = remark;
    }

    public CourseApplyExport() {
    }



    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }


    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getUserStudentid() {
        return userStudentid;
    }

    public void setUserStudentid(String userStudentid) {
        this.userStudentid = userStudentid;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }

    public String getApplyAdmin1() {
        return applyAdmin1;
    }

    public void setApplyAdmin1(String applyAdmin1) {
        this.applyAdmin1 = applyAdmin1;
    }

    public String getApplyAdmin2() {
        return applyAdmin2;
    }

    public void setApplyAdmin2(String applyAdmin2) {
        this.applyAdmin2 = applyAdmin2;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getRecallStatus() {
        return recallStatus;
    }

    public void setRecallStatus(String recallStatus) {
        this.recallStatus = recallStatus;
    }

    public String getRecallReason() {
        return recallReason;
    }

    public void setRecallReason(String recallReason) {
        this.recallReason = recallReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }



    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getApplyAdmin1Name() {
        return applyAdmin1Name;
    }

    public void setApplyAdmin1Name(String applyAdmin1Name) {
        this.applyAdmin1Name = applyAdmin1Name;
    }

    public String getApplyAdmin2Name() {
        return applyAdmin2Name;
    }

    public void setApplyAdmin2Name(String applyAdmin2Name) {
        this.applyAdmin2Name = applyAdmin2Name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

