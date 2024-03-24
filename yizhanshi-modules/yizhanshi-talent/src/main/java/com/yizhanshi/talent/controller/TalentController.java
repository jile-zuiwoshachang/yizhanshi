package com.yizhanshi.talent.controller;

import com.yizhanshi.common.core.utils.StringUtils;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.system.api.domain.SysRole;
import com.yizhanshi.talent.domain.Label;
import com.yizhanshi.talent.domain.TalentLabel;
import com.yizhanshi.talent.domain.vo.Talent;
import com.yizhanshi.talent.domain.vo.TalentExport;
import com.yizhanshi.talent.service.ILabelService;
import com.yizhanshi.talent.service.ITalentLabelService;
import com.yizhanshi.talent.service.ITalentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
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
    @Autowired
    ITalentLabelService talentLabelService;
    @Autowired
    ILabelService labelService;

    /**
     * 人才查询
     * 根据标签
     */
    @RequiresPermissions("business:talent:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody Talent talent) {
        startPage();
        List<Talent> list = talentService.selectTalentList(talent);
        list.forEach(talentTemp -> {
            Long[] labelIds = talentLabelService.selectLabelIdsByUserStudentid(talentTemp.getSysUser().getUserStudentid()).stream().toArray(Long[]::new);
            List<Label> labelList=new ArrayList<>();
            if(labelIds.length!=0){
                labelList = labelService.selectLabelByIds(labelIds);
            }
            talentTemp.setLabels(labelList);
        });
        return getDataTable(list);
    }

    /**
     * 人才注册
     * 传入标签以及个人简介，图片
     */
    @RequiresPermissions("system:talent:edit")
    @Log(title = "人才管理", businessType = BusinessType.INSERT)
    @PutMapping("/registerTalent")
    public AjaxResult registerTalent(@RequestBody Talent talent) {
        //先修改个人简介和图片
        if (talentService.updateUser(talent.getSysUser())) {
            //然后修改标签
            talentService.updateTalent(talent);
            return success();
        } else {
            return error("修改失败");
        }
    }

    /**
     * 人才修改——管理层
     * 修改标签和用户信息
     */
    @RequiresPermissions("business:talent:edit")
    @PutMapping
    public AjaxResult update(@RequestBody Talent talent) {
        talentService.updateTalent(talent);
        return success();
    }

    /**
     * 人才导出
     */
    @Log(title = "人才管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:talent:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Talent talent) {
        List<Talent> list = talentService.selectTalentList(talent);
        List<TalentExport> talentExportList =new ArrayList<>();
        list.forEach(talentTemp -> {
            TalentExport talentExport = new TalentExport();
            BeanUtils.copyProperties(talentTemp.getSysUser(), talentExport);
            Long[] labelIds = talentLabelService.selectLabelIdsByUserStudentid(talentTemp.getSysUser().getUserStudentid()).stream().toArray(Long[]::new);

            if(labelIds.length!=0){
                List<Label> labelList = labelService.selectLabelByIds(labelIds);
                String labelNames = labelList.stream()
                        .map(Label::getLabelName)
                        .collect(Collectors.joining(","));
                talentExport.setLableNames(labelNames);
            }
            talentExportList.add(talentExport);
        });
        ExcelUtil<TalentExport> util = new ExcelUtil<TalentExport>(TalentExport.class);
        util.exportExcel(response, talentExportList, "人才信息数据");
    }


}
