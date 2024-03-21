package com.yizhanshi.place.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
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
import com.yizhanshi.common.security.annotation.InnerAuth;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.course.api.RemoteCourseService;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.domain.CourseTime;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import com.yizhanshi.place.domain.PlaceApplyTimeRelated;
import com.yizhanshi.place.domain.constants.ApplyConstants;
import com.yizhanshi.place.domain.constants.ReasonConstants;
import com.yizhanshi.place.domain.vo.PlaceApplyExport;
import com.yizhanshi.place.service.*;
import com.yizhanshi.system.api.RemoteCreditService;
import com.yizhanshi.system.api.RemoteUserService;
import com.yizhanshi.system.api.domain.SysCredit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 场地预约信息 业务处理
 *
 * @author hejiale
 */
@RefreshScope
@RestController
@RequestMapping("/placeApply")
public class PlaceApplyController extends BaseController {
    @Autowired
    private IPlaceApplyService placeApplyService;
    @Autowired
    private ITimeService timeService;
    @Autowired
    private RemoteCreditService remoteCreditService;
    @Autowired
    private IPlaceReasonService placeReasonService;
    @Autowired
    private IPlaceApplyTimeService placeApplyTimeService;
    @Autowired
    private IPlaceApplyTimeRelatedService placeApplyTimeRelatedService;


    @Value("${app.placeRecallCredit}")
    private String placeRecallCredit;


    /**
     * 查看所有场地预约信息-管理员使用
     * places.placeCampus  北校区  南校区
     * status 0已预约（1） 2已通过 5已拒绝 4已撤销
     */
    @RequiresPermissions("business:placeApply:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody PlaceApply placeApply) {
        startPage();
        List<PlaceApply> list = placeApplyService.selectPlaceApplyList(placeApply);
        for (PlaceApply placeApplyTemp : list) {
            Long[] applyTimeIds = placeApplyTimeRelatedService.selectApplyTimeIdsByApplyId(placeApplyTemp.getApplyId())
                    .stream().toArray(Long[]::new);
            List<PlaceApplyTime> placeApplyTimeList = placeApplyTimeService.selectPlaceApplyTimeByIds(applyTimeIds);
            placeApplyTemp.setPlaceApplyTimes(placeApplyTimeList);
        }
        return getDataTable(list);
    }

    /**
     * 个人场地预约记录
     * 参数 userStudentid status  0已预约（1） 2已通过 5已拒绝 4已撤销
     */
    @RequiresPermissions("business:placeApply:mylist")
    @GetMapping("/mylist")
    public TableDataInfo mylist(@RequestBody PlaceApply placeApply) {
        startPage();
        List<PlaceApply> list = placeApplyService.selectPlaceApplyList(placeApply);
        for (PlaceApply placeApplyTemp : list) {
            Long[] applyTimeIds = placeApplyTimeRelatedService.selectApplyTimeIdsByApplyId(placeApplyTemp.getApplyId())
                    .stream().toArray(Long[]::new);
            List<PlaceApplyTime> placeApplyTimeList = placeApplyTimeService.selectPlaceApplyTimeByIds(applyTimeIds);
            placeApplyTemp.setPlaceApplyTimes(placeApplyTimeList);
        }
        return getDataTable(list);
    }

    /**
     * 根据id查询具体信息
     *
     * @param applyId
     * @return
     */
    @RequiresPermissions("business:placeApply:query")
    @GetMapping("/{applyId}")
    public AjaxResult query(@PathVariable Long applyId) {
        Long[] applyTimeIds = placeApplyTimeRelatedService.selectApplyTimeIdsByApplyId(applyId)
                .stream().toArray(Long[]::new);
        List<PlaceApplyTime> placeApplyTimeList = placeApplyTimeService.selectPlaceApplyTimeByIds(applyTimeIds);
        PlaceApply placeApply = placeApplyService.selectPlaceApplyById(applyId);
        placeApply.setPlaceApplyTimes(placeApplyTimeList);
        return success(placeApply);
    }

    /**
     * 修改场地预约信息——管理员使用
     *
     * @param placeApply
     * @return
     */
    @RequiresPermissions("business:placeApply:edit")
    @Log(title = "场地预约管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editPlaceApply(@Validated @RequestBody PlaceApply placeApply) {
        //不可修改时间只能删除
        placeApply.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(placeApplyService.updatePlaceApply(placeApply));
    }

    /**
     * 删除场地预约——管理员使用
     */
    @RequiresPermissions("business:place:remove")
    @Log(title = "场地预约管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyIds}")
    public AjaxResult removePlaceApply(@PathVariable Long[] applyIds) {
        placeApplyService.deletePlaceApply(applyIds);
        return success();
    }

    /**
     * 导出场地预约——一二管理员使用
     *
     * @param response
     * @param placeApply
     */
    @Log(title = "场地预约管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:placeApply:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody PlaceApply placeApply) {
        List<PlaceApply> list = placeApplyService.selectPlaceApplyList(placeApply);
        List<PlaceApplyExport> placeApplyExportList = new ArrayList<>();

        list.forEach(placeApplyTemp -> {
            Long[] applyTimeIds = placeApplyTimeRelatedService.selectApplyTimeIdsByApplyId(placeApplyTemp.getApplyId())
                    .stream()
                    .toArray(Long[]::new);
            List<PlaceApplyTime> placeApplyTimeList = placeApplyTimeService.selectPlaceApplyTimeByIds(applyTimeIds);
            placeApplyTimeList.forEach(placeApplyTime -> {
                PlaceApplyExport placeApplyExport = new PlaceApplyExport();
                BeanUtils.copyProperties(placeApplyTemp, placeApplyExport); // 先复制PlaceApply的共通属性
                // 然后设置特定于PlaceApplyTime的属性
                placeApplyExport.setApplyDay(placeApplyTime.getApplyDay());
                placeApplyExport.setApplyStartTime(placeApplyTime.getApplyStartTime());
                placeApplyExport.setApplyEndTime(placeApplyTime.getApplyEndTime());
                placeApplyExport.setPlaceName(placeApplyTime.getPlace().getPlaceName());
                placeApplyExport.setPlaceCampus(placeApplyTime.getPlace().getPlaceCampus());
                placeApplyExport.setReasonName(placeApplyTemp.getPlaceReason().getReasonName());
                placeApplyExport.setReasonType(placeApplyTemp.getPlaceReason().getReasonType());
                placeApplyExportList.add(placeApplyExport);
            });
        });

        ExcelUtil<PlaceApplyExport> util = new ExcelUtil<PlaceApplyExport>(PlaceApplyExport.class);
        util.exportExcel(response, placeApplyExportList, "场地预约数据");
    }

    /**
     * 新增场地预约（业务层使用）
     */
    @RequiresPermissions("business:placeApply:add")
    @Log(title = "场地预约管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addPlaceApply(@Validated @RequestBody PlaceApply placeApply) {
        //判断时间冲突
        //查询那个场地那天的预约情况 拿出预约时间列表的第一个判断冲突
        for (PlaceApplyTime placeApplyTime : placeApply.getPlaceApplyTimes()) {
            String str = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, placeApplyTime.getApplyDay());
            List<PlaceApplyTime> dataBasePlaceTimeApplies = placeApplyTimeService.selectAllPlace(placeApplyTime.getPlaceId(), str);
            //先判断所选择天数的所在场地第一天的情况，然后循环判断
            if (placeApplyService.timeConflict(dataBasePlaceTimeApplies, placeApplyTime)) {
                return error("时间冲突!请查看当天场地预约信息后修改时间");
            }
            //再远程调用课程服务，判断时间是否冲突
            if(timeConflict(placeApply.getPlaceApplyTimes(), str)){
                return error("时间冲突！请查看当天课程预约信息后修改时间");
            }
        }
        placeApply.setCreateBy(SecurityUtils.getUsername());
        placeApplyService.insertPlaceApply(placeApply);
        return success();
    }
    private Boolean timeConflict(List<PlaceApplyTime> placeApplyTimes, String str) {
        //再远程调用课程服务，判断时间是否冲突
        List<CourseTime> courseTimes = new ArrayList<>();
        for (PlaceApplyTime placeApplyTime : placeApplyTimes) {
            CourseTime courseTime=new CourseTime();
            courseTime.setTimeStartId(placeApplyTime.getTimeStartId());
            courseTime.setTimeEndId(placeApplyTime.getTimeEndId());
            courseTime.setPlaceId(placeApplyTime.getPlaceId());
            Map<String, Object> params = new HashMap<>();
            params.put("chooseDay", str);
            courseTime.setParams(params);
        }
        return placeApplyService.timeConflictByCourse(courseTimes);
    }

    /**
     * 一级管理员审核场地预约
     * status只能是1 5
     * 支持多选同意
     */
    @RequiresPermissions("business:placeApply1:check")
    @Log(title = "场地预约管理", businessType = BusinessType.UPDATE)
    @PutMapping("/check1")
    public AjaxResult check1(@RequestBody List<PlaceApply> placeApplyList) {
        for (PlaceApply temp : placeApplyList) {
            if (StringUtils.equals(temp.getStatus(), ApplyConstants.YIADMINAGREESTATUS)) {
                //同意预约，则根据原因判断
                if (StringUtils.equals(placeReasonService.selectReasonById(temp.getReasonId()).getReasonType(), ReasonConstants.SMALLREASON)) {
                    temp.setStatus(ApplyConstants.AGREESTATUS);
                }
                if (StringUtils.equals(placeReasonService.selectReasonById(temp.getReasonId()).getReasonType(), ReasonConstants.BIGREASON)) {
                    temp.setStatus(ApplyConstants.YIADMINAGREESTATUS);
                }
            }
            //拒绝就还是5，不变
            temp.setUpdateBy(SecurityUtils.getUsername());
            temp.setApplyAdmin1(SecurityUtils.getUserStudentid());
            temp.setApplyAdmin1Name(SecurityUtils.getUsername());
            //修改为不可撤销
            temp.setRecallStatus(ApplyConstants.RECALLNOT);
        }
        placeApplyService.updatePlaceApplyList(placeApplyList);
        return success();
    }

    /**
     * 二级管理员审核场地预约
     * status只能是2 5
     */
    @RequiresPermissions("business:placeApply2:check")
    @Log(title = "场地预约管理", businessType = BusinessType.UPDATE)
    @PutMapping("/check2")
    public AjaxResult check2(@RequestBody List<PlaceApply> placeApplyList) {
        for (PlaceApply temp : placeApplyList) {
            //不分大小活动直接通过为2，拒绝为5
            temp.setUpdateBy(SecurityUtils.getUsername());
            temp.setApplyAdmin2(SecurityUtils.getUserStudentid());
            temp.setApplyAdmin2Name(SecurityUtils.getUsername());
            //修改为不可撤销
            temp.setRecallStatus(ApplyConstants.RECALLNOT);
        }
        placeApplyService.updatePlaceApplyList(placeApplyList);
        return success();
    }

    /**
     * 用户撤销预约
     * 传递撤销理由和预约单号和status和recallStatus
     * 返回时前端根据recallStatus，为0可点撤销按钮，不可点强行撤销；为1不可点撤销按钮，可点强行撤销按钮；为2什么都不可点
     * 有两个按钮 撤销和强行撤销（扣除5分）
     */
    @RequiresPermissions("business:placeApply:recall")
    @Log(title = "场地预约管理", businessType = BusinessType.UPDATE)
    @PutMapping("/recall")
    public AjaxResult applyRecall(@RequestBody PlaceApply placeApply) {
        //普通撤销
        if (StringUtils.equals(placeApply.getRecallStatus(), ApplyConstants.RECALLCAN)) {
            placeApply.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(placeApplyService.updatePlaceApply(placeApply));
        }
        //强行撤销
        if (StringUtils.equals(placeApply.getRecallStatus(), ApplyConstants.RECALLNOT)) {
            placeApply.setRecallStatus(ApplyConstants.RECALLCREDIT);
            SysCredit sysCredit = new SysCredit(null, "用户强行撤销场地预约", "自己操作", SecurityUtils.getUserStudentid(), Convert.toInt(placeRecallCredit, -2), null, "0", "0");
            R<Boolean> booleanR = remoteCreditService.addUserCredit(sysCredit, SecurityConstants.INNER);
            if (R.FAIL == booleanR.getCode()) {
                throw new ServiceException(booleanR.getMsg());
            }
            placeApply.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(placeApplyService.updatePlaceApply(placeApply));
        }
        return error("撤销失败或者参数不足");
    }

    /**
     * 信誉管理
     *
     * @param sysCredit
     * @return
     */
    @RequiresPermissions("business:placeApply:credit")
    @Log(title = "场地预约管理", businessType = BusinessType.INSERT)
    @PostMapping("/credit")
    public AjaxResult credit(@RequestBody SysCredit sysCredit) {
        sysCredit.setCreditSource("管理员操作");
        sysCredit.setAdminName(SecurityUtils.getUsername());
        R<Boolean> booleanR = remoteCreditService.addUserCredit(sysCredit, SecurityConstants.INNER);
        if (R.FAIL == booleanR.getCode()) {
            throw new ServiceException(booleanR.getMsg());
        }
        return success();
    }

    @InnerAuth
    @PostMapping("/timeConflictByPlace")
    public R<Boolean> timeConflictByPlace(@RequestBody List<PlaceApplyTime> placeApplyTimes) {
        //str为日期字符串
        for (PlaceApplyTime placeApplyTime : placeApplyTimes) {
            String str = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, placeApplyTime.getApplyDay());
            List<PlaceApplyTime> dataBasePlaceTimeApplies = placeApplyTimeService.selectAllPlace(placeApplyTime.getPlaceId(), str);
            //先判断所选择天数的所在场地第一天的情况，然后循环判断
            if (placeApplyService.timeConflict(dataBasePlaceTimeApplies, placeApplyTime)) {
                return R.fail("时间冲突!请查看当天场地预约信息后修改时间");
            }
        }
        return R.ok(Boolean.FALSE);
    }


}
