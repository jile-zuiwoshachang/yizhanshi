package com.yizhanshi.lost.controller;


import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.lost.domain.Lost;
import com.yizhanshi.lost.domain.vo.LostExport;
import com.yizhanshi.lost.service.ILostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务层——失物信息
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/lostInfo")
public class LostController extends BaseController {
    
    @Autowired
    private ILostService lostService;
    /**
     * 失物信息-管理员使用
     */
    @RequiresPermissions("business:lost:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody Lost lost) {
        startPage();
        List<Lost> lostList=lostService.selectLostList(lost);
        return getDataTable(lostList);
    }
    /**
     * 失物信息-查看自己发布的
     * 需传pickStudentid
     */
    @RequiresPermissions("business:lost:mylist")
    @GetMapping("/mylist")
    public TableDataInfo mylist(@RequestBody Lost lost) {
        startPage();
        List<Lost> lostList=lostService.selectLostList(lost);
        return getDataTable(lostList);
    }

    /**
     * 导出失物信息——管理员使用
     *
     * @param response
     * @param lost
     */
    @Log(title = "失物管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:lost:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Lost lost) {
        List<Lost> list = lostService.selectLostList(lost);
        List<LostExport> lostExportList=new ArrayList<>();
        list.forEach(lostTemp->{
            LostExport lostExport=new LostExport();
            BeanUtils.copyProperties(lostTemp,lostExport);
            lostExportList.add(lostExport);
        });
        ExcelUtil<LostExport> util = new ExcelUtil<LostExport>(LostExport.class);
        util.exportExcel(response, lostExportList, "失物信息数据");
    }

    /**
     * 根据失物编号获取详细信息
     */
    @RequiresPermissions("business:lost:query")
    @GetMapping(value = {"/{lostId}"})
    public AjaxResult getInfo(@PathVariable(value = "lostId") Long lostId) {
        Lost lost=lostService.selectLostById(lostId);
        return success(lost);
    }

    /**
     * 新增失物信息
     *
     *
     * @param lost
     * @return
     */
    @RequiresPermissions("business:lost:add")
    @Log(title = "失物管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addLost(@Validated @RequestBody Lost lost) {
        lost.setCreateBy(SecurityUtils.getUsername());
        return toAjax(lostService.insertLost(lost));
    }
    /**
     * 修改失物信息-都可使用
     *
     *
     * @param lost
     * @return
     */
    @RequiresPermissions("business:lost:edit")
    @Log(title = "失物管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editLost(@Validated @RequestBody Lost lost) {
        lost.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(lostService.updateLost(lost));
    }
    /**
     * 删除失物——都可使用
     */
    @RequiresPermissions("business:lost:remove")
    @Log(title = "失物管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{lostIds}")
    public AjaxResult removeLost(@PathVariable Long[] lostIds) {
        lostService.deleteLost(lostIds);
        return success();
    }

}
