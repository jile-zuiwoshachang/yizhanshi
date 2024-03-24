package com.yizhanshi.system.controller;

import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.utils.StringUtils;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.InnerAuth;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;

import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.system.mapper.SysCreditMapper;
import com.yizhanshi.system.mapper.SysUserMapper;
import com.yizhanshi.system.service.ISysCreditService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 信誉信息
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/credit")
public class SysCreditController extends BaseController {
    @Autowired
    private ISysCreditService creditService;
    @Autowired
    private SysCreditMapper creditMapper;
    @Autowired
    private SysUserMapper userMapper;
    /**
     * 获取信誉列表
     */
    @RequiresPermissions("system:credit:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody SysCredit credit)
    {
        startPage();
        List<SysCredit> list = creditService.selectCreditList(credit);
        return getDataTable(list);
    }
    @Log(title = "信誉管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:credit:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody SysCredit credit)
    {
        List<SysCredit> list = creditService.selectCreditList(credit);
        ExcelUtil<SysCredit> util = new ExcelUtil<SysCredit>(SysCredit.class);
        util.exportExcel(response, list, "信誉数据");
    }

    /**
     * 新增信誉信息
     * @param credit
     * @return
     */
    @Log(title = "信誉管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addUserCredit(@Validated @RequestBody SysCredit credit) {
        String userStudentid=credit.getUserStudentid();
        if(StringUtils.isEmpty(userStudentid)){
            return error("学号为空，请联系管理员");
        }
        SysUser sysUser=userMapper.selectUserByUserStudentid(userStudentid);
        if(ObjectUtils.isEmpty(sysUser)){
            return error("该用户不存在");
        }
        int newScore=sysUser.getUserScore()+credit.getCreditNumber();
        if(newScore>100 || newScore<0){
            return error("该用户信誉已经超过或低于限定范围，请重新修改信誉分");
        }
        credit.setCreateBy(SecurityUtils.getUsername());
        if(creditMapper.insertCredit(credit)>0){
            userMapper.updateUserScore(newScore,userStudentid);
            return success();
        }else{
            return error("新增信誉失败");
        }
    }
    /**
     * 新增信誉信息
     * @param credit
     * @return
     */
    @Log(title = "信誉管理", businessType = BusinessType.INSERT)
    @InnerAuth
    @PostMapping
    public R<Boolean> addUserCreditByInner(@Validated @RequestBody SysCredit credit) {
        String userStudentid=credit.getUserStudentid();
        if(StringUtils.isEmpty(userStudentid)){
            return R.fail("学号为空，请联系管理员");
        }
        SysUser sysUser=userMapper.selectUserByUserStudentid(userStudentid);
        if(ObjectUtils.isEmpty(sysUser)){
            return R.fail("该用户不存在");
        }
        int newScore=sysUser.getUserScore()+credit.getCreditNumber();
        if(newScore>100 || newScore<0){
            return R.fail("该用户信誉已经超过或低于限定范围，请重新修改信誉分");
        }
        credit.setCreateBy(SecurityUtils.getUsername());
        if(creditMapper.insertCredit(credit)>0){
            return R.ok(userMapper.updateUserScore(newScore,userStudentid)>0);
        }else{
            return R.fail("新增信誉失败");
        }
    }
    /**
     * 不需要修改函数
     */

    /**
     * 删除信誉记录
     */
    @RequiresPermissions("system:credit:remove")
    @Log(title = "信誉管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{creditIds}")
    public AjaxResult remove(@PathVariable Long[] creditIds)
    {
        return toAjax(creditService.deleteCreditByIds(creditIds));
    }

}
