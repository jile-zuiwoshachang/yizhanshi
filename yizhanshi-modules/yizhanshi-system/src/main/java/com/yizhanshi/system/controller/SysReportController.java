package com.yizhanshi.system.controller;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.system.api.domain.SysReport;
import com.yizhanshi.system.service.ISysReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 举报 信息操作处理
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/report")
public class SysReportController extends BaseController {
    @Autowired
    private ISysReportService reportService;

    /**
     * 获取举报信息列表
     */
    @RequiresPermissions("system:report:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody SysReport report)
    {
        startPage();
        List<SysReport> list = reportService.selectReportList(report);
        return getDataTable(list);
    }
    /**
     * 获取举报信息列表
     * 获取个人举报列表
     */
    @RequiresPermissions("system:report:mylist")
    @GetMapping("/mylist")
    public TableDataInfo mylist(@RequestBody SysReport report)
    {
        startPage();
        List<SysReport> list = reportService.selectReportList(report);
        return getDataTable(list);
    }

    /**
     * 根据举报信息编号获取详细信息
     */
    @RequiresPermissions("system:report:query")
    @GetMapping(value = "/{reportId}")
    public AjaxResult getInfo(@PathVariable Long reportId)
    {
        return success(reportService.selectReportById(reportId));
    }

    @Log(title = "举报信息", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:report:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody SysReport report)
    {
        List<SysReport> list = reportService.selectReportList(report);
        ExcelUtil<SysReport> util = new ExcelUtil<SysReport>(SysReport.class);
        util.exportExcel(response, list, "举报信息数据");
    }
    /**
     * 新增举报信息
     */
    @RequiresPermissions("system:report:add")
    @Log(title = "举报信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysReport report)
    {
        report.setCreateBy(SecurityUtils.getUsername());
        return toAjax(reportService.insertReport(report));
    }

    /**
     * 修改举报信息  管理员功能
     */
    @RequiresPermissions("system:report:edit")
    @Log(title = "举报信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysReport report)
    {
        report.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(reportService.updateReport(report));
    }

    /**
     * 删除举报信息
     */
    @RequiresPermissions("system:report:remove")
    @Log(title = "举报信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(reportService.deleteReportByIds(reportIds));
    }
    /**
     * 审核举报——管理员
     * 前端传递status和reportId和原因内容等
     * 虽然和修改差不多，但是根据业务以及维护上，区分开比较好
     */
    @RequiresPermissions("system:report:check")
    @Log(title = "举报信息", businessType = BusinessType.UPDATE)
    @PutMapping("/check")
    public AjaxResult checkReport(@RequestBody SysReport report)
    {
        report.setReportAdmin(SecurityUtils.getUserStudentid());
        report.setReportAdminName(SecurityUtils.getUsername());
        return toAjax(reportService.updateReport(report));
    }
}
