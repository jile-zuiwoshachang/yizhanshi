package com.yizhanshi.course.api.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.annotation.Excel.ColumnType;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Teacher extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "老师序号", cellType = ColumnType.NUMERIC)
    private Long teacherId;
    @Excel(name = "老师名称")
    private  String teacherName;
    @Excel(name = "老师性别",readConverterExp = "0=男,1=女,2=未知")
    private String teacherSex;
    @Excel(name = "老师年龄", cellType = ColumnType.NUMERIC)
    private  int teacherAge;
    @Excel(name = "老师学号")
    private  String teacherUserStudentid;
    @Excel(name = "老师介绍")
    private  String teacherDescription;
    @Excel(name = "老师描述")
    private  String teacherPicture;
    @Excel(name = "老师邮箱")
    private  String teacherEmail;
    @Excel(name = "老师电话")
    private  String teacherPhone;
    @Excel(name = "老师QQ")
    private  String teacherQq;
    @Excel(name = "老师微信")
    private  String teacherWeChat;

    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    private String delFlag;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex;
    }

    public int getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(int teacherAge) {
        this.teacherAge = teacherAge;
    }

    public String getTeacherUserStudentid() {
        return teacherUserStudentid;
    }

    public void setTeacherUserStudentid(String teacherUserStudentid) {
        this.teacherUserStudentid = teacherUserStudentid;
    }

    public String getTeacherDescription() {
        return teacherDescription;
    }

    public void setTeacherDescription(String teacherDescription) {
        this.teacherDescription = teacherDescription;
    }

    public String getTeacherPicture() {
        return teacherPicture;
    }

    public void setTeacherPicture(String teacherPicture) {
        this.teacherPicture = teacherPicture;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherQq() {
        return teacherQq;
    }

    public void setTeacherQq(String teacherQq) {
        this.teacherQq = teacherQq;
    }

    public String getTeacherWeChat() {
        return teacherWeChat;
    }

    public void setTeacherWeChat(String teacherWeChat) {
        this.teacherWeChat = teacherWeChat;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("teacherId", teacherId)
                .append("teacherName", teacherName)
                .append("teacherSex", teacherSex)
                .append("teacherAge", teacherAge)
                .append("teacherUserStudentid", teacherUserStudentid)
                .append("teacherDescription", teacherDescription)
                .append("teacherPicture", teacherPicture)
                .append("teacherEmail", teacherEmail)
                .append("teacherPhone", teacherPhone)
                .append("teacherQq", teacherQq)
                .append("teacherWeChat", teacherWeChat)
                .append("status", status)
                .append("delFlag", delFlag)
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("remark", getRemark())
                .toString();
    }
}
