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
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.domain.constants.ApplyConstants;
import com.yizhanshi.place.domain.constants.ReasonConstants;
import com.yizhanshi.place.domain.vo.PlaceApplyExport;
import com.yizhanshi.place.service.IPlaceApplyService;
import com.yizhanshi.place.service.IPlaceReasonService;
import com.yizhanshi.place.service.ITimeService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 场地申请信息 业务处理
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


    @Value("${app.placeRecallCredit}")
    private String placeRecallCredit;



    /**
     *
     * 查看所有场地申请信息-管理员使用
     * places.placeCampus  0北校区  1南校区
     * status 0/1审核中 2已通过 5已拒绝 4已撤销
     */
    @RequiresPermissions("business:placeApply:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody PlaceApply placeApply) {
        startPage();
        List<PlaceApply> list = placeApplyService.selectPlaceApplyList(placeApply);
        return getDataTable(list);
    }
    /**
     * 个人场地预约记录
     * 参数 userStudentid status
     */
    @RequiresPermissions("business:placeApply:mylist")
    @GetMapping("/mylist")
    public TableDataInfo mylist(@RequestBody PlaceApply placeApply) {
        startPage();
        List<PlaceApply> list = placeApplyService.selectPlaceApplyList(placeApply);
        return getDataTable(list);
    }

    @RequiresPermissions("business:placeApply:query")
    @GetMapping("/{applyId}")
    public AjaxResult query(@PathVariable Long  applyId) {
        return success(placeApplyService.selectPlaceApplyById(applyId));
    }

    /**
     * 修改场地申请信息——管理员使用
     * @param placeApply
     * @return
     */
    @RequiresPermissions("business:placeApply:edit")
    @Log(title = "场地申请管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editPlaceApply(@Validated @RequestBody PlaceApply placeApply)
    {
        //判断时间冲突
        //查询那个场地那天的申请情况
        String str=DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,placeApply.getApplyDay());
        List<PlaceApply> dataBasePlaceApplies = placeApplyService.selectAllPlace(placeApply.getPlaceId(),str);
        if(placeApplyService.timeConflict(dataBasePlaceApplies,placeApply)){
            return error("时间冲突!请查看当天场地预约信息后修改时间");
        }
        //再远程调用课程服务，判断时间是否冲突
        Course course=new Course();
        course.setTimeStartId(placeApply.getTimeStartId());
        course.setTimeEndId(placeApply.getTimeEndId());
        course.setCourseStartTime(placeApply.getApplyStartTime());
        course.setCourseEndTime(placeApply.getApplyEndTime());
        course.setPlaceId(placeApply.getPlaceId());
        Map<String,Object> params=new HashMap<>();
        params.put("chooseDay",str);
        course.setParams(params);
        if(placeApplyService.timeConflictByCourse(course)) {
            return error("与课程预约冲突，请检查课程预约时间");
        }
        placeApply.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(placeApplyService.updatePlaceApply(placeApply));
    }

    /**
     * 删除场地申请——管理员使用
     */
    @RequiresPermissions("business:place:remove")
    @Log(title = "场地申请管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyIds}")
    public AjaxResult removePlaceApply(@PathVariable Long[] applyIds)
    {
        return toAjax(placeApplyService.deletePlaceApply(applyIds));
    }

    /**
     * 导出场地申请——一二管理员使用
     * @param response
     * @param placeApply
     */
    @Log(title = "场地申请管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:placeApply:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response,@RequestBody PlaceApply placeApply)
    {
        List<PlaceApply> list = placeApplyService.selectPlaceApplyList(placeApply);
        List<PlaceApplyExport> courseExportList = list.stream()
                .map(placeApplyTemp -> {
                    PlaceApplyExport placeApplyExport = new PlaceApplyExport();
                    BeanUtils.copyProperties(placeApplyTemp, placeApplyExport);
                    placeApplyExport.setPlaceName(placeApplyTemp.getPlaces().getPlaceName());
                    placeApplyExport.setPlaceCampus(placeApplyTemp.getPlaces().getPlaceCampus());
                    placeApplyExport.setReasonName(placeApplyTemp.getPlaceReasons().getReasonName());
                    placeApplyExport.setReasonType(placeApplyTemp.getPlaceReasons().getReasonType());
                    return placeApplyExport;
                })
                .collect(Collectors.toList());
        ExcelUtil<PlaceApplyExport> util = new ExcelUtil<PlaceApplyExport>(PlaceApplyExport.class);
        util.exportExcel(response, courseExportList, "场地申请数据");
    }

    /**
     * 新增场地申请（业务层使用）
     */
    @RequiresPermissions("business:placeApply:add")
    @Log(title = "场地申请管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addPlaceApply(@Validated @RequestBody PlaceApply placeApply)
    {
        //判断时间冲突
        //查询那个场地那天的申请情况
         String str=DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,placeApply.getApplyDay());
        List<PlaceApply> dataBasePlaceApplies = placeApplyService.selectAllPlace(placeApply.getPlaceId(),str);
        if(placeApplyService.timeConflict(dataBasePlaceApplies,placeApply)){
            return error("时间冲突!请查看当天场地预约信息后修改时间");
        }
        //再远程调用课程服务，判断时间是否冲突
        Course course=new Course();
        course.setTimeStartId(placeApply.getTimeStartId());
        course.setTimeEndId(placeApply.getTimeEndId());
        course.setCourseStartTime(placeApply.getApplyStartTime());
        course.setCourseEndTime(placeApply.getApplyEndTime());
        course.setPlaceId(placeApply.getPlaceId());
        Map<String,Object> params=new HashMap<>();
        params.put("chooseDay",str);
        course.setParams(params);
        if(placeApplyService.timeConflictByCourse(course)) {
                return error("与课程预约冲突，请检查课程预约时间");
        }
        placeApply.setCreateBy(SecurityUtils.getUsername());
        return toAjax(placeApplyService.insertPlaceApply(placeApply));
    }
    /**
     * 一级管理员审核场地申请
     * status只能是1 5
     */
    @RequiresPermissions("business:placeApply1:check")
    @Log(title = "场地申请管理", businessType = BusinessType.UPDATE)
    @PutMapping("/check1")
    public AjaxResult check1(@RequestBody PlaceApply placeApply){
        if(StringUtils.equals(placeReasonService.selectReasonById(placeApply.getReasonId()).getReasonType(),ReasonConstants.SMALLREASON) ){
            placeApply.setStatus(ApplyConstants.AGREESTATUS);
        }
        if(StringUtils.equals(placeReasonService.selectReasonById(placeApply.getReasonId()).getReasonType(),ReasonConstants.BIGREASON)){
            placeApply.setStatus(ApplyConstants.YIADMINAGREESTATUS);
        }
        placeApply.setUpdateBy(SecurityUtils.getUsername());
        placeApply.setApplyAdmin1(SecurityUtils.getUserStudentid());
        placeApply.setApplyAdmin1Name(SecurityUtils.getUsername());
        //修改为不可撤销
        placeApply.setRecallStatus(ApplyConstants.RECALLNOT);
        return   toAjax(placeApplyService.updatePlaceApply(placeApply));
    }
    /**
     * 二级管理员审核场地申请
     * status只能是2 5
     */
    @RequiresPermissions("business:placeApply2:check")
    @Log(title = "场地申请管理", businessType = BusinessType.UPDATE)
    @PutMapping("/check2")
    public AjaxResult check2(@RequestBody PlaceApply placeApply){
        //不分大小活动直接通过为2，拒绝为5
        placeApply.setUpdateBy(SecurityUtils.getUsername());
        placeApply.setApplyAdmin2(SecurityUtils.getUserStudentid());
        placeApply.setApplyAdmin2Name(SecurityUtils.getUsername());
        //修改为不可撤销
        placeApply.setRecallStatus(ApplyConstants.RECALLNOT);
        return   toAjax(placeApplyService.updatePlaceApply(placeApply));
    }
    /**
     * 用户撤销申请
     * 传递撤销理由和申请单号和status和recallStatus
     * 返回时前端根据recallStatus，为0可点撤销按钮，不可点强行撤销；为1不可点撤销按钮，可点强行撤销按钮；为2什么都不可点
     * 有两个按钮 撤销和强行撤销（扣除5分）
     */
    @PutMapping("/recall")
    public  AjaxResult applyRecall(@RequestBody  PlaceApply placeApply){
        //普通撤销
        if(StringUtils.equals(placeApply.getRecallStatus(),ApplyConstants.RECALLCAN)){
            placeApply.setUpdateBy(SecurityUtils.getUsername());
           return  toAjax(placeApplyService.updatePlaceApply(placeApply)) ;
        }
        //强行撤销
        if(StringUtils.equals(placeApply.getRecallStatus(),ApplyConstants.RECALLNOT)){
            placeApply.setRecallStatus(ApplyConstants.RECALLCREDIT);
            SysCredit sysCredit=new SysCredit(null,"用户强行撤销场地申请","自己操作",SecurityUtils.getUserStudentid(), Convert.toInt(placeRecallCredit,-2),null,"0","0");
            R<Boolean> booleanR= remoteCreditService.addUserCredit(sysCredit, SecurityConstants.INNER);
            if(R.FAIL == booleanR.getCode()){
                throw new ServiceException(booleanR.getMsg());
            }
            placeApply.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(placeApplyService.updatePlaceApply(placeApply));
        }
        return  error("撤销失败或者参数不足");
    }

    /**
     * 信誉管理
     *
     * @param sysCredit
     * @return
     */
    @RequiresPermissions("business:placeApply:credit")
    @PostMapping("/credit")
    public AjaxResult credit(@RequestBody SysCredit sysCredit){
        sysCredit.setCreditSource("管理员操作");
        sysCredit.setAdminName(SecurityUtils.getUsername());
        R<Boolean> booleanR = remoteCreditService.addUserCredit(sysCredit, SecurityConstants.INNER);
        if(R.FAIL == booleanR.getCode()){
            throw new ServiceException(booleanR.getMsg());
        }
        return success();
    }
    @InnerAuth
    @PostMapping("/timeConflictByPlace")
    public R<Boolean> timeConflictByPlace(@RequestBody PlaceApply placeApply){
        //str为日期字符串
        List<PlaceApply> dataBasePlaceApplies = placeApplyService.selectAllPlace(placeApply.getPlaceId(),(String)placeApply.getParams().get("chooseDay"));
        if(placeApplyService.timeConflict(dataBasePlaceApplies,placeApply)){
            return R.fail("时间冲突!请查看当天场地预约信息后修改时间");
        }
        return R.ok(Boolean.FALSE);

    }


}
