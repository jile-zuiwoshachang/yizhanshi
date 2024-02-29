package com.yizhanshi.place.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.common.core.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 场地信息表
 *
 * @author hejiale
 */
public class PlaceApply extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 申请序号 */
    @Excel(name = "申请序号", cellType = Excel.ColumnType.NUMERIC)
    private Long applyId;
    @Excel(name = "申请人名称")
    private String applyName;

    @Excel(name = "申请人学号")
    private String userStudentid;

    @Excel(name = "场地id", cellType = Excel.ColumnType.NUMERIC)
    private Long placeId;

    @Excel(name = "指导老师姓名")
    private String instructorName;

    @Excel(name = "指导老师学号")
    private String instructorStudentid;

    @Excel(name = "指导老师学院")
    private String instructorOrganization;
    @Excel(name = "指导老师电话")
    private String instructorPhone;

    private Date applyDay;


    private Long timeStartId;

    private Long timeEndId;

    @Excel(name = "开始时间")
    private String applyStartTime;
    @Excel(name = "结束时间")
    private String applyEndTime;
    @Excel(name = "申请人数", cellType = Excel.ColumnType.NUMERIC)
    private int applyNumber;
    @Excel(name = "申请原因id", cellType = Excel.ColumnType.NUMERIC)
    private Long reasonId;
    @Excel(name = "申请组织")
    private String applyOrganization;
    @Excel(name = "申请内容")
    private String applyContent;
    @Excel(name = "一级管理员")
    private String applyAdmin1;
    @Excel(name = "二级管理员")
    private String applyAdmin2;
    @Excel(name = "拒绝理由")
    private String refuseReason;
    @Excel(name = "可撤销标志",readConverterExp = "0可撤销 1不可撤销 2已花费信誉撤销")
    private String recallStatus;
    @Excel(name = "撤销理由")
    private String recallReason;
    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0已申请  1一级通过 2 二级通过 4撤销 5拒绝")
    private String status;
    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    private Place places;
    private PlaceReason placeReasons;

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

    public String getRecallStatus() {
        return recallStatus;
    }

    public void setRecallStatus(String recallStatus) {
        this.recallStatus = recallStatus;
    }
    @NotNull(message = "申请教室编号不可为null")
    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorStudentid() {
        return instructorStudentid;
    }

    public void setInstructorStudentid(String instructorStudentid) {
        this.instructorStudentid = instructorStudentid;
    }

    public String getInstructorOrganization() {
        return instructorOrganization;
    }

    public void setInstructorOrgnization(String instructorOrganization) {
        this.instructorOrganization = instructorOrganization;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    /**
     * 格式转化为2024-02-01
     * @return
     */
    @NotNull(message = "申请日期不可为null")
    @JsonFormat(pattern = "yyyy-MM-dd")
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

    public int getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(int applyNumber) {
        this.applyNumber = applyNumber;
    }
   @NotNull(message = "申请原因id不能为空")
    public Long getReasonId() {
        return reasonId;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
    }

    public String getApplyOrganization() {
        return applyOrganization;
    }

    public void setApplyOrganization(String applyOrganization) {
        this.applyOrganization = applyOrganization;
    }
    @Xss(message = "申请内容不能包含脚本字符")
    @NotBlank(message = "申请内容不能为空")
    @Size(min = 0, max = 100, message = "申请内容不能超过100个字符")
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
    @Xss(message = "拒绝理由不能包含脚本字符")
    @Size(min = 0, max = 100, message = "拒绝理由不能超过100个字符")
    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
    @Xss(message = "撤销理由不能包含脚本字符")
    @Size(min = 0, max = 100, message = "撤销理由不能超过100个字符")
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

    public Place getPlaces() {
        return places;
    }

    public void setPlaces(Place places) {
        this.places = places;
    }

    public PlaceReason getPlaceReasons() {
        return placeReasons;
    }

    public void setPlaceReasons(PlaceReason placeReasons) {
        this.placeReasons = placeReasons;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("applyId", applyId)
                .append("applyName", applyName)
                .append("userStudentid", userStudentid)
                .append("placeId", placeId)
                .append("instructorName", instructorName)
                .append("instructorStudentid", instructorStudentid)
                .append("instructorOrgaization", instructorOrganization)
                .append("instructorPhone", instructorPhone)
                .append("applyDay", applyDay)
                .append("timeStartId", timeStartId)
                .append("timeEndId", timeEndId)
                .append("applyStartTime", applyStartTime)
                .append("applyEndTime", applyEndTime)
                .append("applyNumber", applyNumber)
                .append("reasonId", reasonId)
                .append("applyOrganization", applyOrganization)
                .append("applyContent", applyContent)
                .append("applyAdmin1", applyAdmin1)
                .append("applyAdmin2", applyAdmin2)
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
