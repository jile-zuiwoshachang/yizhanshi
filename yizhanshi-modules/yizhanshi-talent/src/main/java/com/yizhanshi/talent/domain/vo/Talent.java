package com.yizhanshi.talent.domain.vo;

import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.talent.domain.Label;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 返回给前端的人才类
 */
public class Talent {
    private SysUser sysUser;
    private List<Label> labels;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sysUser", sysUser)
                .append("labels", labels)
                .toString();
    }
}
