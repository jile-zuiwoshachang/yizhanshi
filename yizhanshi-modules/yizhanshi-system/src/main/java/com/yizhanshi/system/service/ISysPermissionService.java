package com.yizhanshi.system.service;

import java.util.Set;

import com.yizhanshi.system.api.domain.SysUser;

/**
 * 权限信息 服务层
 * 
 * @author hejiale
 */
public interface ISysPermissionService
{
    /**
     * 获取角色数据权限
     * 
     * @param userId 用户Id
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user);

    /**
     * 获取菜单数据权限
     * 
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    public Set<String> getResourcePermission(SysUser user);
}
