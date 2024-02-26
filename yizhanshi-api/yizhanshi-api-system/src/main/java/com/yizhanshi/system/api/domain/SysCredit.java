package com.yizhanshi.system.api.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;

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

    public int getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(int creditNumber) {
        this.creditNumber = creditNumber;
    }

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
}
