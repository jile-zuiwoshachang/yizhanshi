package com.yizhanshi.talent.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 人才与标签关联表
 */
public class TalentLabel {

    private Long userStudentid;
    private Long labelId;


    public Long getUserStudentid() {
        return userStudentid;
    }

    public void setUserStudentid(Long userStudentid) {
        this.userStudentid = userStudentid;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userStudentid", userStudentid)
                .append("labelId", labelId)
                .toString();
    }
}
