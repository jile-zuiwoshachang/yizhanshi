package com.yizhanshi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色和菜单关联 sys_role_resource
 * 
 * @author hejiale
 */
public class SysRoleResource
{
    /** 角色ID */
    private Long roleId;
    
    /** 资源ID */
    private Long resourceId;

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(Long resourceId)
    {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("resourceId", getResourceId())
            .toString();
    }
}
