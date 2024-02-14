package com.yizhanshi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yizhanshi.system.domain.SysResource;

/**
 * 资源表 数据层
 * 
 * @author hejiale
 */
public interface SysResourceMapper
{
    /**
     * 查询系统菜单列表
     * 
     * @param resource 菜单信息
     * @return 菜单列表
     */
    public List<SysResource> selectResourceList(SysResource resource);

    /**
     * 根据用户所有权限
     * 
     * @return 权限列表
     */
    public List<String> selectResourcePerms();

    /**
     * 根据用户查询系统菜单列表
     * 
     * @param resource 菜单信息
     * @return 菜单列表
     */
    public List<SysResource> selectResourceListByUserId(SysResource resource);

    /**
     * 根据角色ID查询权限
     * 
     * @param roleId 角色ID
     * @return 权限列表
     */
    public List<String> selectResourcePermsByRoleId(Long roleId);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectResourcePermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单
     * 
     * @return 菜单列表
     */
    public List<SysResource> selectResourceTreeAll();

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysResource> selectResourceTreeByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     * 
     * @param roleId 角色ID
     * @param resourceCheckStrictly 菜单树选择项是否关联显示
     * @return 选中菜单列表
     */
    public List<Long> selectResourceListByRoleId(@Param("roleId") Long roleId, @Param("resourceCheckStrictly") boolean resourceCheckStrictly);

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
     * @return 结果
     */
    public int hasChildByResourceId(Long resourceId);

    /**
     * 新增菜单信息
     * 
     * @param resource 菜单信息
     * @return 结果
     */
    public int insertResource(SysResource resource);

    /**
     * 修改菜单信息
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
     * @param resourceName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    public SysResource checkResourceNameUnique(@Param("resourceName") String resourceName, @Param("parentId") Long parentId);
}
