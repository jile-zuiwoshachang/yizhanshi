package com.yizhanshi.talent.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Label extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "标签主键", cellType = Excel.ColumnType.NUMERIC)
    private Long labelId;
    @Excel(name = "标签名称")
    private  String labelName;
    @Excel(name = "标签类型")
    private String labelType;
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    private String delFlag;
    public Label(){}
    public Label(Long labelId, String labelName, String labelType, String status, String delFlag) {
        this.labelId = labelId;
        this.labelName = labelName;
        this.labelType = labelType;
        this.status = status;
        this.delFlag = delFlag;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
    @NotNull(message = "标签名称不可为null")
    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
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
                .append("labelId", labelId)
                .append("labelName", labelName)
                .append("labelType", labelType)
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
