package com.yizhanshi.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yizhanshi.common.core.constant.Constants;
import com.yizhanshi.common.core.constant.UserConstants;
import com.yizhanshi.common.core.utils.StringUtils;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.system.api.domain.SysRole;
import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.system.domain.SysResource;
import com.yizhanshi.system.domain.vo.MetaVo;
import com.yizhanshi.system.domain.vo.RouterVo;
import com.yizhanshi.system.domain.vo.TreeSelect;
import com.yizhanshi.system.mapper.SysResourceMapper;
import com.yizhanshi.system.mapper.SysRoleMapper;
import com.yizhanshi.system.mapper.SysRoleResourceMapper;
import com.yizhanshi.system.service.ISysResourceService;

/**
 * 菜单 业务层处理
 * 
 * @author hejiale
 */
@Service
public class SysResourceServiceImpl implements ISysResourceService
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private SysResourceMapper resourceMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleResourceMapper roleResourceMapper;

    /**
     * 根据用户查询系统菜单列表
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysResource> selectResourceList(Long userId)
    {
        return selectResourceList(new SysResource(), userId);
    }

    /**
     * 查询系统菜单列表
     * 
     * @param resource 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<SysResource> selectResourceList(SysResource resource, Long userId)
    {
        List<SysResource> resourceList = null;
        // 管理员显示所有菜单信息
        if (SysUser.isAdmin(userId))
        {
            resourceList = resourceMapper.selectResourceList(resource);
        }
        else
        {
            resource.getParams().put("userId", userId);
            resourceList = resourceMapper.selectResourceListByUserId(resource);
        }
        return resourceList;
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectResourcePermsByUserId(Long userId)
    {
        List<String> perms = resourceMapper.selectResourcePermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询权限
     * 
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectResourcePermsByRoleId(Long roleId)
    {
        List<String> perms = resourceMapper.selectResourcePermsByRoleId(roleId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<SysResource> selectResourceTreeByUserId(Long userId)
    {
        List<SysResource> resources = null;
        if (SecurityUtils.isAdmin(userId))
        {
            resources = resourceMapper.selectResourceTreeAll();
        }
        else
        {
            resources = resourceMapper.selectResourceTreeByUserId(userId);
        }
        return getChildPerms(resources, 0);
    }

    /**
     * 根据角色ID查询菜单树信息
     * 
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectResourceListByRoleId(Long roleId)
    {
        SysRole role = roleMapper.selectRoleById(roleId);
        return resourceMapper.selectResourceListByRoleId(roleId, role.isMenuCheckStrictly());
    }

    /**
     * 构建前端路由所需要的菜单
     * 
     * @param resources 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildResources(List<SysResource> resources)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysResource resource : resources)
        {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(resource.getVisible()));
            // 内链并且是顶级菜单设置为空
            router.setName(getRouteName(resource));
            // 内链根据资源类型生成不同的路由地址前缀
            router.setPath(getRouterPath(resource));
            // 根据内外链产生layout，innnerLink,parentView
            router.setComponent(getComponent(resource));
            // 路由参数
            router.setQuery(resource.getQuery());
            router.setMeta(new MetaVo(resource.getResourceName(), resource.getIcon(), StringUtils.equals("1", resource.getIsCache()), resource.getPath()));
            List<SysResource> cResources = resource.getChildren();
            if (StringUtils.isNotEmpty(cResources) && UserConstants.TYPE_DIR.equals(resource.getResourceType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildResources(cResources));
            }
            //内链且为顶级菜单
            else if (isResourceFrame(resource))
            {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(resource.getPath());
                children.setComponent(resource.getComponent());
                children.setName(StringUtils.capitalize(resource.getPath()));
                children.setMeta(new MetaVo(resource.getResourceName(), resource.getIcon(), StringUtils.equals("1", resource.getIsCache()), resource.getPath()));
                children.setQuery(resource.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            // 内链且顶级且为http(s)开头
            else if (resource.getParentId().intValue() == 0 && isInnerLink(resource))
            {
                router.setMeta(new MetaVo(resource.getResourceName(), resource.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(resource.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(resource.getResourceName(), resource.getIcon(), resource.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 构建前端所需要树结构
     * 
     * @param resources 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<SysResource> buildResourceTree(List<SysResource> resources)
    {
        List<SysResource> returnList = new ArrayList<SysResource>();
        List<Long> tempList = resources.stream().map(SysResource::getResourceId).collect(Collectors.toList());
        for (Iterator<SysResource> iterator = resources.iterator(); iterator.hasNext();)
        {
            SysResource resource = (SysResource) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(resource.getParentId()))
            {
                recursionFn(resources, resource);
                returnList.add(resource);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = resources;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param resources 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildResourceTreeSelect(List<SysResource> resources)
    {
        List<SysResource> resourceTrees = buildResourceTree(resources);
        return resourceTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据菜单ID查询信息
     * 
     * @param resourceId 菜单ID
     * @return 菜单信息
     */
    @Override
    public SysResource selectResourceById(Long resourceId)
    {
        return resourceMapper.selectResourceById(resourceId);
    }

    /**
     * 是否存在菜单子节点
     * 
     * @param resourceId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByResourceId(Long resourceId)
    {
        int result = resourceMapper.hasChildByResourceId(resourceId);
        return result > 0;
    }

    /**
     * 查询菜单使用数量
     * 
     * @param resourceId 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkResourceExistRole(Long resourceId)
    {
        int result = roleResourceMapper.checkResourceExistRole(resourceId);
        return result > 0;
    }

    /**
     * 新增保存菜单信息
     * 
     * @param resource 菜单信息
     * @return 结果
     */
    @Override
    public int insertResource(SysResource resource)
    {
        return resourceMapper.insertResource(resource);
    }

    /**
     * 修改保存菜单信息
     * 
     * @param resource 菜单信息
     * @return 结果
     */
    @Override
    public int updateResource(SysResource resource)
    {
        return resourceMapper.updateResource(resource);
    }

    /**
     * 删除菜单管理信息
     * 
     * @param resourceId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteResourceById(Long resourceId)
    {
        return resourceMapper.deleteResourceById(resourceId);
    }

    /**
     * 校验菜单名称是否唯一
     * 
     * @param resource 菜单信息
     * @return 结果
     */
    @Override
    public boolean checkResourceNameUnique(SysResource resource)
    {
        Long resourceId = StringUtils.isNull(resource.getResourceId()) ? -1L : resource.getResourceId();
        SysResource info = resourceMapper.checkResourceNameUnique(resource.getResourceName(), resource.getParentId());
        if (StringUtils.isNotNull(info) && info.getResourceId().longValue() != resourceId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 获取路由名称
     * 
     * @param resource 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysResource resource)
    {
        String routerName = StringUtils.capitalize(resource.getPath());
        // 内链并且是顶级菜单（类型为菜单）
        if (isResourceFrame(resource))
        {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     * 
     * @param resource 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysResource resource)
    {
        String routerPath = resource.getPath();
        // 内链并且是菜单和按钮
        if (resource.getParentId().intValue() != 0 && isInnerLink(resource))
        {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 内链并且是顶级目录（类型为目录）
        if (0 == resource.getParentId().intValue() && UserConstants.TYPE_DIR.equals(resource.getResourceType())
                && UserConstants.NO_FRAME.equals(resource.getIsFrame()))
        {
            routerPath = "/" + resource.getPath();
        }
        // 内链并且是顶级菜单（类型为菜单）
        else if (isResourceFrame(resource))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     * InnerLink 是用于在应用内部导航的链接或路径。
     * Layout 是用于定义网页布局的组件或模板，通常包含在整个应用的页面中，确保页面的一致性。
     * ParentView 是在视图层级中用来管理路由视图的父组件，它可能是整个视图层级的根节点。
     * @param resource 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysResource resource)
    {
        // 默认 layout
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(resource.getComponent()) && !isResourceFrame(resource))
        {
            // 菜单且不为顶级 或者 菜单且不为内链
            component = resource.getComponent();
        }
        else if (StringUtils.isEmpty(resource.getComponent()) && resource.getParentId().intValue() != 0 && isInnerLink(resource))
        {
            // 内链且不为顶级且为htpp(s)开头
            component = UserConstants.INNER_LINK;
        }
        else if (StringUtils.isEmpty(resource.getComponent()) && isParentView(resource))
        {
            // 不是顶级且为目录
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     * 顶级，内链，菜单
     * @param resource 菜单信息
     * @return 结果
     */
    public boolean isResourceFrame(SysResource resource)
    {
        return resource.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(resource.getResourceType())
                && resource.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 是否为内链组件
     * 
     * @param resource 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysResource resource)
    {
        return resource.getIsFrame().equals(UserConstants.NO_FRAME) && StringUtils.ishttp(resource.getPath());
    }

    /**
     * 是否为parent_view组件
     * 
     * @param resource 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysResource resource)
    {
        return resource.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(resource.getResourceType());
    }

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysResource> getChildPerms(List<SysResource> list, int parentId)
    {
        List<SysResource> returnList = new ArrayList<SysResource>();
        for (Iterator<SysResource> iterator = list.iterator(); iterator.hasNext();)
        {
            SysResource t = (SysResource) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * 
     * @param list 分类表
     * @param t 子节点
     */
    private void recursionFn(List<SysResource> list, SysResource t)
    {
        // 得到子节点列表
        List<SysResource> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysResource tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysResource> getChildList(List<SysResource> list, SysResource t)
    {
        List<SysResource> tlist = new ArrayList<SysResource>();
        Iterator<SysResource> it = list.iterator();
        while (it.hasNext())
        {
            SysResource n = (SysResource) it.next();
            if (n.getParentId().longValue() == t.getResourceId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysResource> list, SysResource t)
    {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 内链域名特殊字符替换
     * 
     * @return 替换后的内链域名
     */
    public String innerLinkReplaceEach(String path)
    {
        return StringUtils.replaceEach(path, new String[] { Constants.HTTP, Constants.HTTPS, Constants.WWW, ".", ":" },
                new String[] { "", "", "", "/", "/" });
    }
}
