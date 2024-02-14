package com.yizhanshi.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yizhanshi.common.core.constant.UserConstants;
import com.yizhanshi.common.core.utils.StringUtils;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.system.domain.SysResource;
import com.yizhanshi.system.service.ISysResourceService;

/**
 * 菜单信息
 * 
 * @author hejiale
 */
@RestController
@RequestMapping("/resource")
public class SysResourceController extends BaseController
{
    @Autowired
    private ISysResourceService resourceService;

    /**
     * 获取菜单列表
     */
    @RequiresPermissions("system:resource:list")
    @GetMapping("/list")
    public AjaxResult list(SysResource resource)
    {
        Long userId = SecurityUtils.getUserId();
        List<SysResource> resources = resourceService.selectResourceList(resource, userId);
        return success(resources);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @RequiresPermissions("system:resource:query")
    @GetMapping(value = "/{resourceId}")
    public AjaxResult getInfo(@PathVariable Long resourceId)
    {
        return success(resourceService.selectResourceById(resourceId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysResource resource)
    {
        Long userId = SecurityUtils.getUserId();
        List<SysResource> resources = resourceService.selectResourceList(resource, userId);
        return success(resourceService.buildResourceTreeSelect(resources));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleResourceTreeselect/{roleId}")
    public AjaxResult roleResourceTreeselect(@PathVariable("roleId") Long roleId)
    {
        Long userId = SecurityUtils.getUserId();
        List<SysResource> resources = resourceService.selectResourceList(userId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", resourceService.selectResourceListByRoleId(roleId));
        ajax.put("resources", resourceService.buildResourceTreeSelect(resources));
        return ajax;
    }

    /**
     * 新增菜单
     */
    @RequiresPermissions("system:resource:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysResource resource)
    {
        if (!resourceService.checkResourceNameUnique(resource))
        {
            return error("新增菜单'" + resource.getResourceName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(resource.getIsFrame()) && !StringUtils.ishttp(resource.getPath()))
        {
            return error("新增菜单'" + resource.getResourceName() + "'失败，地址必须以http(s)://开头");
        }
        resource.setCreateBy(SecurityUtils.getUsername());
        return toAjax(resourceService.insertResource(resource));
    }

    /**
     * 修改菜单
     */
    @RequiresPermissions("system:resource:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysResource resource)
    {
        if (!resourceService.checkResourceNameUnique(resource))
        {
            return error("修改菜单'" + resource.getResourceName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(resource.getIsFrame()) && !StringUtils.ishttp(resource.getPath()))
        {
            return error("修改菜单'" + resource.getResourceName() + "'失败，地址必须以http(s)://开头");
        }
        else if (resource.getResourceId().equals(resource.getParentId()))
        {
            return error("修改菜单'" + resource.getResourceName() + "'失败，上级菜单不能选择自己");
        }
        resource.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(resourceService.updateResource(resource));
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("system:resource:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{resourceId}")
    public AjaxResult remove(@PathVariable("resourceId") Long resourceId)
    {
        if (resourceService.hasChildByResourceId(resourceId))
        {
            return warn("存在子菜单,不允许删除");
        }
        if (resourceService.checkResourceExistRole(resourceId))
        {
            return warn("菜单已分配,不允许删除");
        }
        return toAjax(resourceService.deleteResourceById(resourceId));
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysResource> resources = resourceService.selectResourceTreeByUserId(userId);
        return success(resourceService.buildResources(resources));
    }
}