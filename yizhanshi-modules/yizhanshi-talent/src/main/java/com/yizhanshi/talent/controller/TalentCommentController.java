package com.yizhanshi.talent.controller;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.talent.domain.TalentApply;
import com.yizhanshi.talent.domain.TalentComment;
import com.yizhanshi.talent.domain.vo.TalentApplyExport;
import com.yizhanshi.talent.service.ITalentCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 人才评价 信息操作处理
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/talentComment")
public class TalentCommentController extends BaseController {
    @Autowired
    private ITalentCommentService talentCommentService;

    /**
     * 获取人才评价信息列表
     */
    @RequiresPermissions("business:talentComment:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody TalentComment talentComment)
    {
        startPage();
        List<TalentComment> list = talentCommentService.selectTalentCommentList(talentComment);
        return getDataTable(list);
    }

    /**
     * 根据人才评价信息编号获取详细信息
     */
    @RequiresPermissions("system:talentComment:query")
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable Long commentId)
    {
        return success(talentCommentService.selectTalentCommentById(commentId));
    }
    @Log(title = "人才评价管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:talentComment:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody TalentComment talentComment)
    {
        List<TalentComment> list = talentCommentService.selectTalentCommentList(talentComment);
        ExcelUtil<TalentComment> util = new ExcelUtil<TalentComment>(TalentComment.class);
        util.exportExcel(response, list, "人才评价数据");
    }
    /**
     * 新增人才评价信息
     * 根据applyId同时新增talentApply表和talentComment表
     */
    @RequiresPermissions("system:talentComment:add")
    @Log(title = "人才评价信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody TalentComment talentComment)
    {
        talentComment.setCreateBy(SecurityUtils.getUsername());
        talentCommentService.insertTalentComment(talentComment);
        return success();
    }

    /**
     * 修改人才评价信息
     */
    @RequiresPermissions("system:talentComment:edit")
    @Log(title = "人才评价信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody TalentComment talentComment)
    {
        talentComment.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(talentCommentService.updateTalentComment(talentComment));
    }

    /**
     * 删除人才评价信息
     */
    @RequiresPermissions("system:talentComment:remove")
    @Log(title = "人才评价信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds)
    {
       talentCommentService.deleteTalentCommentByIds(commentIds);
       return success();
    }
}
