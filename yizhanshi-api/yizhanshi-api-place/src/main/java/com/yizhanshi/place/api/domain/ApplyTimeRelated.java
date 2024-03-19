package com.yizhanshi.place.api.domain;

import com.yizhanshi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 预约与时间关联表
 */
public class ApplyTimeRelated {

    private Long applyId;
    private Long applyTimeId;


    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getApplyTimeId() {
        return applyTimeId;
    }

    public void setApplyTimeId(Long applyTimeId) {
        this.applyTimeId = applyTimeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("applyId", applyId)
                .append("applyTimeId", applyTimeId)
                .toString();
    }
}
