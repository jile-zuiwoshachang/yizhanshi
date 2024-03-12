package com.yizhanshi.talent.controller;

import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.system.api.domain.SysRole;
import com.yizhanshi.talent.domain.Label;
import com.yizhanshi.talent.domain.vo.Talent;
import com.yizhanshi.talent.service.ITalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 人才信息 业务处理
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/talentInfo")
public class TalentController extends BaseController {
    @Autowired
    ITalentService talentService;

    /**
     * 人才查询
     * 根据标签
     */
    @RequiresPermissions("business:talent:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody Talent label) {
        startPage();
        List<Talent> list = talentService.selectTalentList(label);
        return getDataTable(list);
    }
    /**
     * 人才注册
     * 传入标签以及个人简介，图片
     */
    @RequiresPermissions("system:talent:edit")
    @Log(title = "人才管理", businessType = BusinessType.INSERT)
    @PutMapping("/registerTalent")
    public AjaxResult registerTalent(@RequestBody Talent talent)
    {
        //先修改个人简介和图片
        if(talentService.updateUser(talent.getSysUser())){
            Long userStudentid= Long.valueOf(SecurityUtils.getUserStudentid());
            Long[] labelIds = talent.getLabels().stream().map(Label::getLabelId)
                    .toArray(Long[]::new);
            return toAjax(talentService.insertTalentLabel(userStudentid, labelIds));
        }else{
            return error("修改失败");
        }
    }



}
