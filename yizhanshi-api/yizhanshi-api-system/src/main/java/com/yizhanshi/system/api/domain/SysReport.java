package com.yizhanshi.system.api.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;

/**
 * 举报对象 sys_report
 *
 * @author yizhanshi
 */

public class SysReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 举报ID */
    @Excel(name = "举报序号", cellType = Excel.ColumnType.NUMERIC, prompt = "举报单编号")
    private Long reportId;
    @Excel(name = "举报人名称")
    private String reportName;
    /** 学号 */
    @Excel(name = "举报人学号")
    private String userStudentid;
    @Excel(name = "被举报人名称")
    private String reportedName;
    /** 学号 */
    @Excel(name = "被举报人学号")
    private String reportedStudentid;

    /** 举报内容 */
    @Excel(name = "举报内容")
    private String reportContent;

    /** 举报图片 */
    @Excel(name = "举报图片")
    private String reportPicture;

    /** 操作管理员学号 */
    @Excel(name = "操作管理员学号")
    private String reportAdmin;
    /** 操作管理员姓名 */
    @Excel(name = "操作管理员名称")
    private String reportAdminName;
    /** 拒绝原因 */
    @Excel(name = "拒绝原因")
    private String refuseReason;
    @Excel(name = "处理结果")
    private String handleContent;
    @Excel(name = "状态",readConverterExp = "0=已申请,2=已同意,5=已拒绝")
    private String status;
    /**0=存在  2=删除**/
    private String delFlag;

    public SysReport() {
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
    @NotNull(message = "举报人名称不能为空")
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    @NotNull(message = "举报人学号不能为空")
    public String getUserStudentid() {
        return userStudentid;
    }

    public void setUserStudentid(String userStudentid) {
        this.userStudentid = userStudentid;
    }

    public String getReportedName() {
        return reportedName;
    }

    public void setReportedName(String reportedName) {
        this.reportedName = reportedName;
    }

    public String getReportedStudentid() {
        return reportedStudentid;
    }

    public void setReportedStudentid(String reportedStudentid) {
        this.reportedStudentid = reportedStudentid;
    }
    @NotNull(message = "举报内容不能为空")
    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getReportPicture() {
        return reportPicture;
    }

    public void setReportPicture(String reportPicture) {
        this.reportPicture = reportPicture;
    }

    public String getReportAdmin() {
        return reportAdmin;
    }

    public void setReportAdmin(String reportAdmin) {
        this.reportAdmin = reportAdmin;
    }

    public String getReportAdminName() {
        return reportAdminName;
    }

    public void setReportAdminName(String reportAdminName) {
        this.reportAdminName = reportAdminName;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
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
        return new ToStringBuilder(this)
                .append("reportId", reportId)
                .append("reportName", reportName)
                .append("userStudentid", userStudentid)
                .append("reportedName", reportedName)
                .append("reportedStudentid", reportedStudentid)
                .append("reportContent", reportContent)
                .append("reportPicture", reportPicture)
                .append("reportAdmin", reportAdmin)
                .append("reportAdminName", reportAdminName)
                .append("refuseReason", refuseReason)
                .append("handleContent", handleContent)
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

