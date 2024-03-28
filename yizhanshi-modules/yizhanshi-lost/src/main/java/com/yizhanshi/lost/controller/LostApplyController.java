package com.yizhanshi.lost.controller;

import com.yizhanshi.common.core.utils.StringUtils;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.lost.domain.Lost;
import com.yizhanshi.lost.domain.LostApply;
import com.yizhanshi.lost.domain.constants.ApplyConstants;
import com.yizhanshi.lost.domain.vo.LostApplyExport;
import com.yizhanshi.lost.domain.vo.LostExport;
import com.yizhanshi.lost.service.ILostApplyService;
import com.yizhanshi.lost.service.ILostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务层——失物认领认领信息
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/lostApply")
public class LostApplyController extends BaseController {
    @Autowired
    private ILostApplyService lostApplyService;
    @Autowired
    private ILostService lostService;
    /**
     * 失物认领信息-管理员使用
     */
    @RequiresPermissions("business:lostApply:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody LostApply lostApply) {
        startPage();
        List<LostApply> lostApplyList=lostApplyService.selectLostApplyList(lostApply);
        return getDataTable(lostApplyList);
    }
    /**
     * 失物认领信息-自己的认领信息
     * 我认领的
     * 需传user_studentid
     */
    @RequiresPermissions("business:lostApply:mylist")
    @GetMapping("/mylist")
    public TableDataInfo mylist(@RequestBody LostApply lostApply) {
        startPage();
        List<LostApply> lostApplyList=lostApplyService.selectLostApplyList(lostApply);
        return getDataTable(lostApplyList);
    }

    /**
     * 导出失物认领信息——管理员使用
     *
     * @param response
     * @param lostApply
     */
    @Log(title = "失物认领管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:lostApply:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody LostApply lostApply) {
        List<LostApply> list = lostApplyService.selectLostApplyList(lostApply);
        List<LostApplyExport> lostApplyExportList=new ArrayList<>();
        list.forEach(lostApplyTemp->{
            LostApplyExport lostApplyExport=new LostApplyExport();
            BeanUtils.copyProperties(lostApplyTemp,lostApplyExport);
            lostApplyExport.setLostName(lostApplyTemp.getLost().getLostName());
            lostApplyExport.setLostType(lostApplyTemp.getLost().getLostType());
            lostApplyExport.setPickPlace(lostApplyTemp.getLost().getPickPlace());
            lostApplyExport.setPickCampus(lostApplyTemp.getLost().getPickCampus());
            lostApplyExport.setPickDay(lostApplyTemp.getLost().getPickDay());
            lostApplyExport.setPickStudentid(lostApplyTemp.getLost().getPickStudentid());
            lostApplyExport.setPickName(lostApplyTemp.getLost().getPickName());
            lostApplyExport.setPickStorePlace(lostApplyTemp.getLost().getPickStorePlace());
            lostApplyExportList.add(lostApplyExport);
        });
        ExcelUtil<LostApplyExport> util = new ExcelUtil<LostApplyExport>(LostApplyExport.class);
        util.exportExcel(response, lostApplyExportList, "失物认领信息数据");
    }

    /**
     * 根据失物认领编号获取详细信息
     */
    @RequiresPermissions("business:lostApply:query")
    @GetMapping(value = {"/{applyId}"})
    public AjaxResult getInfo(@PathVariable(value = "applyId") Long applyId) {
        LostApply lostApply=lostApplyService.selectLostApplyById(applyId);
        return success(lostApply);
    }

    /**
     * 新增失物认领信息
     *
     *
     * @param lostApply
     * @return
     */
    @RequiresPermissions("business:lostApply:add")
    @Log(title = "失物认领管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addLostApply(@Validated @RequestBody LostApply lostApply) {
        lostApply.setCreateBy(SecurityUtils.getUsername());
        return toAjax(lostApplyService.insertLostApply(lostApply));
    }
    /**
     * 修改失物认领信息-管理员使用
     * 因为失物一旦发布用户不可修改
     *
     *
     * @param lostApply
     * @return
     */
    @RequiresPermissions("business:lostApply:edit")
    @Log(title = "失物认领管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editLostApply(@Validated @RequestBody LostApply lostApply) {
        lostApply.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(lostApplyService.updateLostApply(lostApply));
    }
    /**
     * 审核失物认领信息
     * @param lostApply
     * @return
     */
    @RequiresPermissions("business:lostApply:check")
    @Log(title = "失物认领管理", businessType = BusinessType.UPDATE)
    @PutMapping("/check")
    public AjaxResult check(@Validated @RequestBody LostApply lostApply) {
        //同意则同意认领，修改失物状态
        if(StringUtils.equals (lostApply.getStatus(), ApplyConstants.AGREESTATUS)){
            Lost lost=new Lost();
            lost.setLostId(lostApply.getLostId());
            lost.setStatus(ApplyConstants.APPLYSUCCESS);
            lostService.updateLost(lost);
        }
        return toAjax(lostApplyService.updateLostApply(lostApply));
    }
    /**
     * 删除失物认领——管理员使用
     */
    @RequiresPermissions("business:lostApply:remove")
    @Log(title = "失物认领管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyIds}")
    public AjaxResult removeLostApply(@PathVariable Long[] applyIds) {
        lostApplyService.deleteLostApply(applyIds);
        return success();
    }
}
