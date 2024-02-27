package com.yizhanshi.place.controller;

import com.yizhanshi.common.core.utils.DateUtils;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.place.domain.Place;
import com.yizhanshi.place.domain.PlaceApply;
import com.yizhanshi.place.service.IPlaceApplyService;
import com.yizhanshi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 场地申请信息 业务处理
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/placeApply")
public class PlaceApplyController extends BaseController {
    @Autowired
    private IPlaceApplyService placeApplyService;

    /**
     * 场地申请信息-管理员使用
     */
    @RequiresPermissions("business:placeApply:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody PlaceApply placeApply) {
        startPage();
        List<PlaceApply> list = placeApplyService.selectPlaceApplyList(placeApply);
        return getDataTable(list);
    }

    /**
     * 修改场地申请
     * @param placeApply
     * @return
     */
    @RequiresPermissions("business:placeApply:edit")
    @Log(title = "场地申请管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editPlaceApply(@Validated @RequestBody PlaceApply placeApply)
    {
        placeApply.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(placeApplyService.updatePlaceApply(placeApply));
    }

    /**
     * 删除场地申请
     */
    @RequiresPermissions("business:place:remove")
    @Log(title = "场地申请管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{placeApplyIds}")
    public AjaxResult removePlaceApply(@PathVariable Long[] placeApplyIds)
    {
        return toAjax(placeApplyService.deletePlaceApply(placeApplyIds));
    }

    /**
     * 导出场地申请
     * @param response
     * @param placeApply
     */
    @Log(title = "场地申请管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:placeApply:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PlaceApply placeApply)
    {
        List<PlaceApply> list = placeApplyService.selectPlaceApplyList(placeApply);
        ExcelUtil<PlaceApply> util = new ExcelUtil<PlaceApply>(PlaceApply.class);
        util.exportExcel(response, list, "场地申请数据");
    }

    /**
     * 新增场地申请
     */
    @RequiresPermissions("business:placeApply:add")
    @Log(title = "场地申请管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addPlaceApply(@Validated @RequestBody PlaceApply placeApply)
    {
        placeApply.setCreateBy(SecurityUtils.getUsername());
        return toAjax(placeApplyService.insertPlaceApply(placeApply));
    }


}
