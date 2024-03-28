package com.yizhanshi.lost.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.system.api.domain.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

public class LostApply extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "失物认领序号", cellType = Excel.ColumnType.NUMERIC)
    private Long applyId;
    @Excel(name = "认领人名称")
    private String applyName;
    @Excel(name = "认领人学号")
    private String userStudentid;
    @Excel(name = "认领原因或者内容")
    private String applyContent;
    @Excel(name = "失物序号", cellType = Excel.ColumnType.NUMERIC)
    private Long lostId;
    @Excel(name = "拒绝原因")
    private String refuseReason;
    @Excel(name = "状态",readConverterExp = "0=已申请,2=已同意,5=已拒绝")
    private String status;
    private String delFlag;
    /**
     * 失物信息
     */
    private Lost lost;

    /**
     * 丢失者（认领人）信息
     */
    private SysUser sysUser;
    public LostApply(){}
    public LostApply(Long applyId, String applyName, String userStudentid, String applyContent, Long lostId, String refuseReason, String status, String delFlag) {
        this.applyId = applyId;
        this.applyName = applyName;
        this.userStudentid = userStudentid;
        this.applyContent = applyContent;
        this.lostId = lostId;
        this.refuseReason = refuseReason;
        this.status = status;
        this.delFlag = delFlag;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }
    @NotNull(message = "认领人姓名不能为空")
    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }
    @NotNull(message = "认领人学号不能为空")
    public String getUserStudentid() {
        return userStudentid;
    }

    public void setUserStudentid(String userStudentid) {
        this.userStudentid = userStudentid;
    }

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }
    @NotNull(message = "失物序号不能为空")
    public Long getLostId() {
        return lostId;
    }

    public void setLostId(Long lostId) {
        this.lostId = lostId;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
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

    public Lost getLost() {
        return lost;
    }

    public void setLost(Lost lost) {
        this.lost = lost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("applyId", applyId)
                .append("applyName", applyName)
                .append("userStudentid", userStudentid)
                .append("applyContent", applyContent)
                .append("lostId", lostId)
                .append("refuseReason", refuseReason)
                .append("status", status)
                .append("delFlag", delFlag)
                .append("lost", lost)
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
