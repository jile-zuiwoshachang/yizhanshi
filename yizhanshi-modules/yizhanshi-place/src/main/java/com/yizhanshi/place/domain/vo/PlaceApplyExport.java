package com.yizhanshi.place.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.common.core.annotation.Excel;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlaceApplyExport {
    private static final long serialVersionUID = 1L;
    /** 预约序号 */
    @Excel(name = "预约序号", cellType = Excel.ColumnType.NUMERIC)
    private Long applyId;
    @Excel(name = "预约人名称")
    private String applyName;

    @Excel(name = "预约人学号")
    private String userStudentid;
    @Excel(name = "场地校区")
    private String placeCampus;

    @Excel(name = "场地名称")
    private String placeName;

    @Excel(name = "指导老师姓名")
    private String instructorName;

    @Excel(name = "指导老师学号")
    private String instructorStudentid;

    @Excel(name = "指导老师学院")
    private String instructorOrganization;
    @Excel(name = "指导老师电话")
    private String instructorPhone;
    @Excel(name = "辅导员姓名")
    private String fudaoName;

    @Excel(name = "辅导员学号")
    private String fudaoStudentid;

    @Excel(name = "辅导员学院")
    private String fudaoOrganization;
    @Excel(name = "辅导员电话")
    private String fudaoPhone;
    @Excel(name="预约日期",dateFormat = "yyyy-MM-dd")
    private Date applyDay;
    @Excel(name = "开始时间")
    private String applyStartTime;
    @Excel(name = "结束时间")
    private String applyEndTime;
    @Excel(name = "预约人数", cellType = Excel.ColumnType.NUMERIC)
    private int applyNumber;
    @Excel(name = "预约原因名称")
    private String reasonName;
    @Excel(name = "预约原因类型")
    private String reasonType;
    @Excel(name = "预约组织")
    private String applyOrganization;
    @Excel(name = "预约内容")
    private String applyContent;
    @Excel(name = "一级管理员学号")
    private String applyAdmin1;
    @Excel(name = "一级管理员名称")
    private String applyAdmin1Name;
    @Excel(name = "二级管理员学号")
    private String applyAdmin2;
    @Excel(name = "二级管理员名称")
    private String applyAdmin2Name;
    @Excel(name = "拒绝理由")
    private String refuseReason;
    @Excel(name = "可撤销标志",readConverterExp = "0=可撤销,1=不可撤销,2=已花费信誉撤销")
    private String recallStatus;
    @Excel(name = "撤销理由")
    private String recallReason;
    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=已预约,1=一级通过,2=二级通过,4=撤销,5=拒绝")
    private String status;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public PlaceApplyExport() {
    }

    public PlaceApplyExport(Long applyId, String applyName, String userStudentid, String placeCampus, String placeName, String instructorName, String instructorStudentid, String instructorOrganization, String instructorPhone, String fudaoName, String fudaoStudentid, String fudaoOrganization, String fudaoPhone, Date applyDay, String applyStartTime, String applyEndTime, int applyNumber, String reasonName, String reasonType, String applyOrganization, String applyContent, String applyAdmin1, String applyAdmin1Name, String applyAdmin2Name, String refuseReason, String recallStatus, String recallReason, String status,Date createTime) {
        this.applyId = applyId;
        this.applyName = applyName;
        this.userStudentid = userStudentid;
        this.placeCampus = placeCampus;
        this.placeName = placeName;
        this.instructorName = instructorName;
        this.instructorStudentid = instructorStudentid;
        this.instructorOrganization = instructorOrganization;
        this.instructorPhone = instructorPhone;
        this.fudaoName = fudaoName;
        this.fudaoStudentid = fudaoStudentid;
        this.fudaoOrganization = fudaoOrganization;
        this.fudaoPhone = fudaoPhone;
        this.applyDay = applyDay;
        this.applyStartTime = applyStartTime;
        this.applyEndTime = applyEndTime;
        this.applyNumber = applyNumber;
        this.reasonName = reasonName;
        this.reasonType = reasonType;
        this.applyOrganization = applyOrganization;
        this.applyContent = applyContent;
        this.applyAdmin1 = applyAdmin1;
        this.applyAdmin1Name = applyAdmin1Name;
        this.applyAdmin2Name = applyAdmin2Name;
        this.refuseReason = refuseReason;
        this.recallStatus = recallStatus;
        this.recallReason = recallReason;
        this.status = status;
        this.createTime = createTime;
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

    public String getPlaceCampus() {
        return placeCampus;
    }

    public void setPlaceCampus(String placeCampus) {
        this.placeCampus = placeCampus;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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

    public void setInstructorOrganization(String instructorOrganization) {
        this.instructorOrganization = instructorOrganization;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getFudaoName() {
        return fudaoName;
    }

    public void setFudaoName(String fudaoName) {
        this.fudaoName = fudaoName;
    }

    public String getFudaoStudentid() {
        return fudaoStudentid;
    }

    public void setFudaoStudentid(String fudaoStudentid) {
        this.fudaoStudentid = fudaoStudentid;
    }

    public String getFudaoOrganization() {
        return fudaoOrganization;
    }

    public void setFudaoOrganization(String fudaoOrganization) {
        this.fudaoOrganization = fudaoOrganization;
    }

    public String getFudaoPhone() {
        return fudaoPhone;
    }

    public void setFudaoPhone(String fudaoPhone) {
        this.fudaoPhone = fudaoPhone;
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

    public int getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(int applyNumber) {
        this.applyNumber = applyNumber;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public String getApplyOrganization() {
        return applyOrganization;
    }

    public void setApplyOrganization(String applyOrganization) {
        this.applyOrganization = applyOrganization;
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

    public String getApplyAdmin2() {
        return applyAdmin2;
    }

    public void setApplyAdmin2(String applyAdmin2) {
        this.applyAdmin2 = applyAdmin2;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
