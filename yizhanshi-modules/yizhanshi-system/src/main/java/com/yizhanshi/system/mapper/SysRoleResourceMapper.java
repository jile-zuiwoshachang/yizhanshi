package com.yizhanshi.system.mapper;

import java.util.List;
import com.yizhanshi.system.domain.SysRoleResource;

/**
 * 角色与菜单关联表 数据层
 * 
 * @author hejiale
 */
public interface SysRoleResourceMapper
{
    /**
     * 查询菜单使用数量
     * 
     * @param resourceId 菜单ID
     * @return 结果
     */
    public int checkResourceExistRole(Long resourceId);

    /**
     * 通过角色ID删除角色和菜单关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleResourceByRoleId(Long roleId);

    /**
     * 批量删除角色菜单关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleResource(Long[] ids);

    /**
     * 批量新增角色菜单信息
     * 
     * @param roleResourceList 角色菜单列表
     * @return 结果
     */
    public int batchRoleResource(List<SysRoleResource> roleResourceList);
}
