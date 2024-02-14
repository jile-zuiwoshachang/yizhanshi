package com.yizhanshi.system.service;

import java.util.List;
import java.util.Set;
import com.yizhanshi.system.domain.SysResource;
import com.yizhanshi.system.domain.vo.RouterVo;
import com.yizhanshi.system.domain.vo.TreeSelect;

/**
 * 菜单 业务层
 * 
 * @author hejiale
 */
public interface ISysResourceService
{
    /**
     * 根据用户查询系统菜单列表
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysResource> selectResourceList(Long userId);

    /**
     * 根据用户查询系统菜单列表
     * 
     * @param resource 菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysResource> selectResourceList(SysResource resource, Long userId);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectResourcePermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     * 
     * @param roleId 角色ID
     * @return 权限列表
     */
    public Set<String> selectResourcePermsByRoleId(Long roleId);

    /**
     * 根据用户ID查询菜单树信息
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysResource> selectResourceTreeByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     * 
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    public List<Long> selectResourceListByRoleId(Long roleId);

    /**
     * 构建前端路由所需要的菜单
     * 
     * @param resources 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildResources(List<SysResource> resources);

    /**
     * 构建前端所需要树结构
     * 
     * @param resources 菜单列表
     * @return 树结构列表
     */
    public List<SysResource> buildResourceTree(List<SysResource> resources);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param resources 菜单列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildResourceTreeSelect(List<SysResource> resources);

    /**
     * 根据菜单ID查询信息
     * 
     * @param resourceId 菜单ID
     * @return 菜单信息
     */
    public SysResource selectResourceById(Long resourceId);

    /**
     * 是否存在菜单子节点
     * 
     * @param resourceId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean hasChildByResourceId(Long resourceId);

    /**
     * 查询菜单是否存在角色
     * 
     * @param resourceId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkResourceExistRole(Long resourceId);

    /**
     * 新增保存菜单信息
     * 
     * @param resource 菜单信息
     * @return 结果
     */
    public int insertResource(SysResource resource);

    /**
     * 修改保存菜单信息
     * 
     * @param resource 菜单信息
     * @return 结果
     */
    public int updateResource(SysResource resource);

    /**
     * 删除菜单管理信息
     * 
     * @param resourceId 菜单ID
     * @return 结果
     */
    public int deleteResourceById(Long resourceId);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param resource 菜单信息
     * @return 结果
     */
    public boolean checkResourceNameUnique(SysResource resource);


}
