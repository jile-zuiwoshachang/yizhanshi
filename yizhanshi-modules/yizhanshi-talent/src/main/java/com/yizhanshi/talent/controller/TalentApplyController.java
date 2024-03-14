package com.yizhanshi.talent.controller;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.common.core.text.Convert;
import com.yizhanshi.common.core.utils.DateUtils;
import com.yizhanshi.common.core.utils.StringUtils;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.system.api.RemoteUserService;
import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.talent.domain.TalentApply;
import com.yizhanshi.talent.domain.constants.ApplyConstants;
import com.yizhanshi.talent.domain.vo.Talent;
import com.yizhanshi.talent.domain.vo.TalentApplyExport;
import com.yizhanshi.talent.service.ITalentApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 人才预约信息 业务处理
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/talentApply")
public class TalentApplyController extends BaseController {
    @Autowired
    private ITalentApplyService talentApplyService;
    @Autowired
    private RemoteUserService remoteUserService;
    @Value("${app.applyNums}")
    private int applyNums;
    /**
     * 人才预约查询
     *
     */
    @RequiresPermissions("business:talentApply:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody TalentApply talentApply) {
        startPage();
        List<TalentApply> list = talentApplyService.selectTalentApplyList(talentApply);
        return getDataTable(list);
    }
    /**
     * 根据预约编号查询
     * 人才具体信息
     */
    @RequiresPermissions("business:talentApply:query")
    @GetMapping("/{applyId}")
    public AjaxResult query(@PathVariable Long  applyId) {
        return success(talentApplyService.selectTalentApplyById(applyId));
    }

    /**
     * 导出人才预约——二级管理员使用
     * @param response
     * @param talentApply
     */
    @Log(title = "人才预约管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:talentApply:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody TalentApply talentApply)
    {
        List<TalentApply> list = talentApplyService.selectTalentApplyList(talentApply);
        List<TalentApplyExport> talentApplyExportList = list.stream()
                .map(talentApplyTemp -> {
                    TalentApplyExport talentApplyExport = new TalentApplyExport();
                    BeanUtils.copyProperties(talentApplyTemp, talentApplyExport);
                    String talentName=remoteUserService.getUserInfo(talentApplyTemp.getTalentStudentid(), SecurityConstants.INNER).getData().getUsername();
                    talentApplyExport.setTalentName(talentName);
                    return talentApplyExport;
                })
                .collect(Collectors.toList());
        ExcelUtil<TalentApplyExport> util = new ExcelUtil<TalentApplyExport>(TalentApplyExport.class);
        util.exportExcel(response, talentApplyExportList, "人才预约数据");
    }
    /**
     * 新增人才预约
     * 不需要考虑冲突
     * 前端不需要加params
     */
    @RequiresPermissions("business:talentApply:add")
    @Log(title = "人才预约管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addTalentApply(@Validated @RequestBody TalentApply talentApply)
    {
        //判断当天的时间共有几条
        Map<String,Object> params=new HashMap<>();
        params.put("chooseDay",DateUtils.dateTimeTalent());
        talentApply.setParams(params);
        if(talentApplyService.selectTalentApplyList(talentApply).size()<=applyNums){
            talentApply.setCreateBy(SecurityUtils.getUsername());
            return toAjax(talentApplyService.insertTalentApply(talentApply));
        }else{
            return error("今天你的预约已经到达上限，请明天再试");
        }
    }
    /**
     * 修改预约——都可以使用
     */
    @Log(title = "人才预约管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editTalentApply(@Validated @RequestBody TalentApply talentApply)
    {
        talentApply.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(talentApplyService.updateTalentApply(talentApply));
    }
    /**
     * 删除课程预约——管理员使用
     */
    @RequiresPermissions("business:talentApply:remove")
    @Log(title = "人才预约管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyIds}")
    public AjaxResult removeTalentApply(@PathVariable Long[] applyIds)
    {
        return toAjax(talentApplyService.deleteTalentApply(applyIds));
    }
    /**
     * 用户撤销预约
     * 传递撤销理由和预约单号和status和recallStatus
     * 返回时前端根据recallStatus，为0可点撤销按钮，不可点强行撤销；为1不可点撤销按钮，可点强行撤销按钮；为2什么都不可点
     * 有两个按钮 撤销和强行撤销（扣除5分）
     */
    @PutMapping("/recall")
    public  AjaxResult applyRecall(@RequestBody  TalentApply talentApply){
        //普通撤销
        if(StringUtils.equals(talentApply.getRecallStatus(),ApplyConstants.RECALLCAN)){
            talentApply.setUpdateBy(SecurityUtils.getUsername());
            return  toAjax(talentApplyService.updateTalentApply(talentApply)) ;
        }
        //强行撤销
        if(StringUtils.equals(talentApply.getRecallStatus(), ApplyConstants.RECALLNOT)){
            return error("已经撤销");
        }
        return  error("撤销失败或者参数不足");
    }

}
