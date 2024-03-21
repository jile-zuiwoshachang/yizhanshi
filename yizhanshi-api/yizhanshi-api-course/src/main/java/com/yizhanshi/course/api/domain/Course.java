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
import java.util.List;

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
    @Excel(name = "课程容量", cellType = ColumnType.NUMERIC)
    private int courseNumber;
    @Excel(name = "选课状态",readConverterExp = "0=独立,1=不独立")
    private  String chooseStatus;
    @Excel(name = "审核类型",readConverterExp = "0=自动通过,1=一级管理员审核,2=二级管理员审核" )
    private String courseCheck;
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    private String delFlag;

    private Teacher teacher;
    /**
     * 排课用
     */
    private List<CourseTime> courseTimes;
    /**
     * 返回前端用
     * 已选人数
     */
    private int chooseNumber;
    /**
     * 返回前端用
     * 可选课标志
     * true：可
     * false: 不可
     */
    private  Boolean flag;

    public Course(){}
    public Course(Long courseId, String courseName, String courseType, String courseDescription, Long teacherId, int courseNumber, String chooseStatus, String courseCheck, String status, String delFlag, Teacher teacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseDescription = courseDescription;
        this.teacherId = teacherId;
        this.courseNumber = courseNumber;
        this.chooseStatus = chooseStatus;
        this.courseCheck = courseCheck;
        this.status = status;
        this.delFlag = delFlag;
        this.teacher = teacher;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    @NotNull(message = "课程名称不能为空")
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
    @NotNull(message = "老师编号不能为空")
    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getChooseNumber() {
        return chooseNumber;
    }

    public void setChooseNumber(int chooseNumber) {
        this.chooseNumber = chooseNumber;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public List<CourseTime> getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(List<CourseTime> courseTimes) {
        this.courseTimes = courseTimes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("courseId", courseId)
                .append("courseName", courseName)
                .append("courseDescription", courseDescription)
                .append("courseType", courseType)
                .append("teacherId", teacherId)
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
