package com.yizhanshi.talent.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.common.core.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class TalentApplyExport {
    private static final long serialVersionUID = 1L;
    /** 预约序号 */
    @Excel(name = "预约序号", cellType = Excel.ColumnType.NUMERIC)
    private Long applyId;
    @Excel(name = "预约人名称")
    private String applyName;
    @Excel(name = "预约人学号")
    private String userStudentid;
    @Excel(name = "人才学号")
    private String talentStudentid;
    @Excel(name = "人才姓名")
    private String talentName;
    @Excel(name = "预约内容")
    private String applyContent;
    @Excel(name = "开始日期",dateFormat = "yyyy-MM-dd")
    private Date applyStartDay;
    @Excel(name = "结束日期",dateFormat = "yyyy-MM-dd")
    private Date applyEndDay;
    @Excel(name = "状态", readConverterExp = "0=已申请,2=已同意,4=已撤销,5=已拒绝")
    private String status;
    @Excel(name = "拒绝理由")
    private String refuseReason;
    @Excel(name = "可撤销标志",readConverterExp = "0=可撤销,1=不可撤销")
    private String recallStatus;
    @Excel(name = "撤销理由")
    private String recallReason;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "创建人")
    private String createBy;

    public TalentApplyExport() {
    }

    public TalentApplyExport(Long applyId, String applyName, String userStudentid, String talentStudentid, String talentName, String applyContent, Date applyStartDay, Date applyEndDay, String status, String refuseReason, String recallStatus, String recallReason, Date createTime, String createBy) {
        this.applyId = applyId;
        this.applyName = applyName;
        this.userStudentid = userStudentid;
        this.talentStudentid = talentStudentid;
        this.talentName = talentName;
        this.applyContent = applyContent;
        this.applyStartDay = applyStartDay;
        this.applyEndDay = applyEndDay;
        this.status = status;
        this.refuseReason = refuseReason;
        this.recallStatus = recallStatus;
        this.recallReason = recallReason;
        this.createTime = createTime;
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getTalentStudentid() {
        return talentStudentid;
    }

    public void setTalentStudentid(String talentStudentid) {
        this.talentStudentid = talentStudentid;
    }

    public String getTalentName() {
        return talentName;
    }

    public void setTalentName(String talentName) {
        this.talentName = talentName;
    }

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }

    public Date getApplyStartDay() {
        return applyStartDay;
    }

    public void setApplyStartDay(Date applyStartDay) {
        this.applyStartDay = applyStartDay;
    }

    public Date getApplyEndDay() {
        return applyEndDay;
    }

    public void setApplyEndDay(Date applyEndDay) {
        this.applyEndDay = applyEndDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("applyId", applyId)
                .append("applyName", applyName)
                .append("userStudentid", userStudentid)
                .append("talentStudentid", talentStudentid)
                .append("talentName", talentName)
                .append("applyContent", applyContent)
                .append("applyStartDay", applyStartDay)
                .append("applyEndDay", applyEndDay)
                .append("status", status)
                .append("refuseReason", refuseReason)
                .append("recallStatus", recallStatus)
                .append("recallReason", recallReason)
                .append("createTime", createTime)
                .append("createBy", createBy)
                .toString();
    }
}
