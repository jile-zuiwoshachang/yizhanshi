package com.yizhanshi.talent.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.system.api.domain.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class TalentApply extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "预约主键", cellType = Excel.ColumnType.NUMERIC)
    private Long applyId;
    @Excel(name = "预约人名称")
    private String applyName;
    @Excel(name = "预约人学号")
    private String userStudentid;
    @Excel(name = "人才学号")
    private String talentStudentid;
    @Excel(name = "预约内容")
    private String applyContent;
    @Excel(name = "开始日期",dateFormat = "yyyy-MM-dd")
    private Date applyStartDay;
    @Excel(name = "结束日期",dateFormat = "yyyy-MM-dd")
    private Date applyEndDay;
    @Excel(name = "状态", readConverterExp = "0=已申请,2=已同意,4=已撤销,5=已拒绝")
    private String status;
    private String delFlag;
    @Excel(name = "拒绝原因")
    private String refuseReason;
    @Excel(name = "撤销原因")
    private String recallReason;
    @Excel(name = "可撤销标志",readConverterExp = "0=可撤销,1=不可撤销")
    private String recallStatus;
    //人才信息
    private SysUser talents;

    public SysUser getTalents() {
        return talents;
    }

    public void setTalents(SysUser talents) {
        this.talents = talents;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getRecallReason() {
        return recallReason;
    }

    public void setRecallReason(String recallReason) {
        this.recallReason = recallReason;
    }

    public String getRecallStatus() {
        return recallStatus;
    }

    public void setRecallStatus(String recallStatus) {
        this.recallStatus = recallStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("applyId", applyId)
                .append("applyName", applyName)
                .append("userStudentid", userStudentid)
                .append("talentStudentid", talentStudentid)
                .append("applyContent", applyContent)
                .append("applyStartDay", applyStartDay)
                .append("applyEndDay", applyEndDay)
                .append("status", status)
                .append("delFlag", delFlag)
                .append("refuseReason", refuseReason)
                .append("recallReason", recallReason)
                .append("recallStatus", recallStatus)
                .append("talents", talents)
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
