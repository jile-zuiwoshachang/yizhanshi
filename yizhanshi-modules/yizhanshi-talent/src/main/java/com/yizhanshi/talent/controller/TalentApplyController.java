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
import com.yizhanshi.system.api.RemoteCreditService;
import com.yizhanshi.system.api.RemoteUserService;
import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.system.api.domain.SysUser;
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
import java.util.ArrayList;
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
    @Autowired
    private RemoteCreditService remoteCreditService;
    @Value("${app.applyNums}")
    private int applyNums;
    /**
     * 人才预约查询——管理员使用
     *
     */
    @RequiresPermissions("business:talentApply:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody TalentApply talentApply) {
        startPage();
        List<TalentApply> list = talentApplyService.selectTalentApplyList(talentApply);
        list.forEach(talentApplyTemp ->{
           SysUser applyUser=remoteUserService.getUserInfo(talentApplyTemp.getUserStudentid(),SecurityConstants.INNER).getData().getSysUser();
            applyUser.setUserPassword(null);
            talentApplyTemp.setApplyUser(applyUser);
        });
        return getDataTable(list);
    }
    /**
     * 查看自己的个人预约记录
     * 需传递用户自己的学号
     *
     */
    @RequiresPermissions("business:talentApply:mylist")
    @GetMapping("/mylist")
    public TableDataInfo mylist(@RequestBody TalentApply talentApply) {
        startPage();
        List<TalentApply> list = talentApplyService.selectTalentApplyList(talentApply);
        list.forEach(talentApplyTemp ->{
            SysUser applyUser=remoteUserService.getUserInfo(talentApplyTemp.getUserStudentid(),SecurityConstants.INNER).getData().getSysUser();
            applyUser.setUserPassword(null);
            talentApplyTemp.setApplyUser(applyUser);
        });
        return getDataTable(list);
    }
    /**
     * 根据预约编号查询
     * 人才预约具体信息
     */
    @RequiresPermissions("business:talentApply:query")
    @GetMapping("/{applyId}")
    public AjaxResult query(@PathVariable Long  applyId) {
        TalentApply talentApply= talentApplyService.selectTalentApplyById(applyId);
        SysUser applyUser=remoteUserService.getUserInfo(talentApply.getUserStudentid(),SecurityConstants.INNER).getData().getSysUser();
        applyUser.setUserPassword(null);
        talentApply.setApplyUser(applyUser);
        return success(talentApply);
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
        List<TalentApplyExport> talentApplyExportList = new ArrayList<>();
        list.forEach(talentApplyTemp -> {
            TalentApplyExport talentApplyExport = new TalentApplyExport();
            BeanUtils.copyProperties(talentApplyTemp, talentApplyExport);
            // 根据数据库查询加入新值
            String talentName = remoteUserService.getUserInfo(talentApplyTemp.getTalentStudentid(), SecurityConstants.INNER).getData().getSysUser().getUserName();
            talentApplyExport.setTalentName(talentName);
            // 添加到新列表
            talentApplyExportList.add(talentApplyExport);
        });
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
        params.put("beginTime",DateUtils.dateTimeTalent());
        talentApply.setParams(params);
        if(talentApplyService.selectTalentApplyList(talentApply).size()<=applyNums){
            talentApply.setCreateBy(SecurityUtils.getUsername());
            return toAjax(talentApplyService.insertTalentApply(talentApply));
        }else{
            return error("今天你的预约已经到达上限"+applyNums+"次，请明天再试");
        }
    }
    /**
     * 修改预约——管理员使用
     *
     */
    @Log(title = "人才预约管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editTalentApply(@Validated @RequestBody TalentApply talentApply)
    {
        talentApply.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(talentApplyService.updateTalentApply(talentApply));
    }
    /**
     * 审核
     * 多选同意
     * 单选删除
     */
    @RequiresPermissions("business:talentApply:check")
    @Log(title = "人才预约管理", businessType = BusinessType.UPDATE)
    @PutMapping("/check")
    public AjaxResult check(@RequestBody List<TalentApply> talentApplyList){
        for(TalentApply temp:talentApplyList) {
            if (StringUtils.equals(temp.getStatus(), ApplyConstants.AGREESTATUS)) {
                temp.setStatus(ApplyConstants.AGREESTATUS);
            }
            temp.setUpdateBy(SecurityUtils.getUsername());
            //修改为不可撤销
            temp.setRecallStatus(ApplyConstants.RECALLNOT);
        }
       talentApplyService.updateTalentApplyList(talentApplyList);
        return success();
    }
    /**
     * 删除课程预约——管理员使用
     */
    @RequiresPermissions("business:talentApply:remove")
    @Log(title = "人才预约管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyIds}")
    public AjaxResult removeTalentApply(@PathVariable Long[] applyIds)
    {
        talentApplyService.deleteTalentApply(applyIds);
        return success();
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
