package com.yizhanshi.course.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.domain.Teacher;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.domain.Teacher;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

public class CourseApply extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "预约序号", cellType = Excel.ColumnType.NUMERIC)
    private Long applyId;
    @Excel(name = "预约人姓名")
    private String applyName;
    @Excel(name = "预约人学号")
    private String userStudentid;
    @Excel(name = "课程序号", cellType = Excel.ColumnType.NUMERIC)
    private Long courseId;
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
    @Excel(name = "状态",readConverterExp = "0=已预约,1=一级通过,2=二级通过, 4=撤销, 5=拒绝")
    private String status;
    private String delFlag;

    private Course course;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }
    @NotNull(message = "申请人名称不能为空")
    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }
    @NotNull(message = "申请人学号不能为空")
    public String getUserStudentid() {
        return userStudentid;
    }

    public void setUserStudentid(String userStudentid) {
        this.userStudentid = userStudentid;
    }
    @NotNull(message = "课程序号不能为空")
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("applyId", applyId)
                .append("applyName", applyName)
                .append("userStudentid", userStudentid)
                .append("courseId", courseId)
                .append("applyContent", applyContent)
                .append("applyAdmin1", applyAdmin1)
                .append("applyAdmin1Name", applyAdmin1Name)
                .append("applyAdmin2", applyAdmin2)
                .append("applyAdmin2Name", applyAdmin2Name)
                .append("refuseReason", refuseReason)
                .append("recallStatus", recallStatus)
                .append("recallReason", recallReason)
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
