package com.yizhanshi.place.domain;

import com.yizhanshi.common.core.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 场地信息表
 *
 * @author hejiale
 */
public class PlaceReason {
    private static final long serialVersionUID = 1L;
    /** 原因序号 */
    private Long reasonId;

    /** 原因名称 */
    private String reasonName;

    /** 原因类型 BIG 大活动； SMALL 小型活动 */
    private String reasonType;

    public Long getReasonId() {
        return reasonId;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("reasonId", reasonId)
                .append("reasonName", reasonName)
                .append("reasonType", reasonType)
                .toString();
    }
}
