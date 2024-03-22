package com.yizhanshi.talent.controller;

import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.talent.domain.Label;
import com.yizhanshi.talent.mapper.LabelMapper;
import com.yizhanshi.talent.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 标签信息 业务处理
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/labelInfo")
public class LabelController extends BaseController {

    @Autowired
    private ILabelService labelService;

    /**
     * 标签信息 查询
     */
    @RequiresPermissions("business:label:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody Label label) {
        startPage();
        List<Label> list = labelService.selectLabelList(label);
        return getDataTable(list);
    }

    /**
     * 导出标签信息——管理员使用
     * @param response
     * @param label
     */
    @Log(title = "标签管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:label:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Label label)
    {
        List<Label> list = labelService.selectLabelList(label);
        ExcelUtil<Label> util = new ExcelUtil<Label>(Label.class);
        util.exportExcel(response, list, "标签信息数据");
    }
    /**
     * 根据标签编号获取详细信息
     */
    @RequiresPermissions("business:label:query")
    @GetMapping(value = { "/{labelId}" })
    public AjaxResult getInfo(@PathVariable(value = "labelId") Long labelId){
        return success(labelService.selectLabelById(labelId));
    }

    /**
     * 新增标签信息
     * @param label
     * @return
     */
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addLabel(@Validated @RequestBody Label label) {

        label.setCreateBy(SecurityUtils.getUsername());
        return toAjax(labelService.insertLabel(label));
    }

    /**
     * 修改标签——管理员使用
     * @param label
     * @return
     */
    @RequiresPermissions("business:label:edit")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editLabel(@Validated @RequestBody Label label)
    {

        label.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(labelService.updateLabel(label));
    }
    /**
     * 删除标签——管理员使用
     * @param labelIds
     * @return
     */
    @RequiresPermissions("business:label:remove")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{labelIds}")
    public AjaxResult removeLabel(@PathVariable("labelIds")Long[] labelIds)
    {
        return toAjax(labelService.deleteLabel(labelIds));
    }
    /**
     * 返回所有的标签类型
     */
    @GetMapping("/allType")
    public AjaxResult allType() {
        List<String> allType = labelService.selectAllType();
        return success(allType);
    }



}
