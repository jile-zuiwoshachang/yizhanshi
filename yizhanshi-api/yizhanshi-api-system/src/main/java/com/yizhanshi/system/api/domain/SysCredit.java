package com.yizhanshi.system.api.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.common.core.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 信誉对象 sys_credit
 *
 * @author yizhanshi
 */

public class SysCredit extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 信誉ID */
    @Excel(name = "信誉序号", cellType = Excel.ColumnType.NUMERIC, prompt = "信誉id")
    private Long creditId;
    @Excel(name = "信誉操作原因")
    private String creditContent;
    @Excel(name = "信誉操作来源", readConverterExp = "自己操作 管理员操作 系统自动操作")
    private String creditSource;
    /** 学号 */
    @Excel(name = "用户学号", type = Excel.Type.EXPORT)
    private String userStudentid;
    /** 操作数字 */
    @Excel(name = "数字（可正负） ")
    private int creditNumber;
    /** 管理员姓名 */
    @Excel(name = "管理员姓名")
    private String adminName;

    @Excel(name = "状态（0启用 1停用）")
    private String status;
    @Excel(name = "删除标志（0存在 1删除）")
    private String delFlag;
    public SysCredit()
    {

    }

    public SysCredit(Long creditId)
    {
        this.creditId = creditId;
    }

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public String getCreditContent() {
        return creditContent;
    }

    public void setCreditContent(String creditContent) {
        this.creditContent = creditContent;
    }

    public String getCreditSource() {
        return creditSource;
    }

    public void setCreditSource(String creditSource) {
        this.creditSource = creditSource;
    }

    public String getUserStudentid() {
        return userStudentid;
    }

    public void setUserStudentid(String userStudentid) {
        this.userStudentid = userStudentid;
    }
    @NotNull(message = "信誉分数不可为空")
    public int getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(int creditNumber) {
        this.creditNumber = creditNumber;
    }
    @Xss(message = "用户姓名不能包含脚本字符")
    @NotBlank(message = "用户姓名不能为空")
    @Size(min = 0, max = 100, message = "用户姓名长度不能超过100个字符")
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("creditId", getCreditId())
                .append("creditContent", getCreditContent())
                .append("creditSource", getCreditSource())
                .append("userStudentid", getUserStudentid())
                .append("creditNumber", getCreditNumber())
                .append("adminName", getAdminName())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
