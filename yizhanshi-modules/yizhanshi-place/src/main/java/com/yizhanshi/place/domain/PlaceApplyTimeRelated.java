package com.yizhanshi.place.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class PlaceApplyTimeRelated {
    private Long applyId;
    private Long applyTimeId;
    public PlaceApplyTimeRelated(){}


    public PlaceApplyTimeRelated(Long applyId, Long applyTimeId) {
        this.applyId = applyId;
        this.applyTimeId = applyTimeId;
    }

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
