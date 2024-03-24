package com.yizhanshi.course.controller;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.common.core.text.Convert;
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
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.domain.CourseTime;
import com.yizhanshi.course.domain.CourseApply;
import com.yizhanshi.course.domain.constants.ApplyConstants;
import com.yizhanshi.course.domain.vo.CourseApplyExport;
import com.yizhanshi.course.domain.vo.CourseExport;
import com.yizhanshi.course.service.ICourseApplyService;
import com.yizhanshi.course.service.ICourseService;
import com.yizhanshi.course.service.ICourseTimeRelatedService;
import com.yizhanshi.course.service.ICourseTimeService;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import com.yizhanshi.system.api.RemoteCreditService;
import com.yizhanshi.system.api.domain.SysCredit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 课程预约信息 业务处理
 *
 * @author hejiale
 */
@RefreshScope
@RestController
@RequestMapping("/courseApply")
public class CourseApplyController extends BaseController {
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ICourseApplyService courseApplyService;
    @Autowired
    private ICourseTimeRelatedService courseTimeRelatedService;
  
    @Autowired
    private RemoteCreditService remoteCreditService;
    @Autowired
    private ICourseTimeService courseTimeService;
   
    @Value("${app.courseRecallCredit}")
    private String courseRecallCredit;



    /**
     *
     * 查看所有课程预约信息-管理员使用
     * userCampus   status 0已预约(1) 2已通过 5已拒绝 4已撤销
     */
    @RequiresPermissions("business:courseApply:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody CourseApply courseApply) {
        startPage();
        List<CourseApply> list = courseApplyService.selectCourseApplyList(courseApply);
        return getDataTable(list);
    }

    /**
     * 个人选课记录查看
     * userStudentid  status 0已预约(1) 2已通过 5已拒绝 4已撤销
     * @param courseApply
     * @return
     */
    @RequiresPermissions("business:courseApply:mylist")
    @GetMapping("/mylist")
    public TableDataInfo mylist(@RequestBody CourseApply courseApply) {
        startPage();
        List<CourseApply> list = courseApplyService.selectCourseApplyList(courseApply);
        return getDataTable(list);
    }

    /**
     * 根据选课id查询详细信息
     * @param applyId
     * @return
     */
    @RequiresPermissions("business:courseApply:query")
    @GetMapping("/{applyId}")
    public AjaxResult query(@PathVariable Long  applyId) {
        return success(courseApplyService.selectCourseApplyById(applyId));
    }

    /**
     * 修改课程预约信息——管理员使用
     * @param courseApply
     * @return
     */
    @RequiresPermissions("business:courseApply:edit")
    @Log(title = "课程预约管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editCourseApply(@Validated @RequestBody CourseApply courseApply)
    {
        courseApply.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(courseApplyService.updateCourseApply(courseApply));
    }

    /**
     * 删除课程预约——管理员使用
     */
    @RequiresPermissions("business:course:remove")
    @Log(title = "课程预约管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyIds}")
    public AjaxResult removeCourseApply(@PathVariable Long[] applyIds)
    {
        return toAjax(courseApplyService.deleteCourseApply(applyIds));
    }

    /**
     * 导出课程预约——一二管理员使用
     * @param response
     * @param courseApply
     */
    @Log(title = "课程预约管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:courseApply:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody CourseApply courseApply)
    {
        List<CourseApply> list = courseApplyService.selectCourseApplyList(courseApply);
        List<CourseApplyExport> courseApplyExportList=new ArrayList<>();
        //根据课程生成选课信息
        list.forEach(courseApplyTemp -> {
                CourseApplyExport courseApplyExport = new CourseApplyExport();
                BeanUtils.copyProperties(courseApplyTemp, courseApplyExport);
                courseApplyExport.setTeacherName(courseApplyTemp.getCourse().getTeacher().getTeacherName());
                courseApplyExport.setCourseName(courseApplyTemp.getCourse().getCourseName());
                courseApplyExport.setCourseType(courseApplyTemp.getCourse().getCourseType());
                courseApplyExportList.add(courseApplyExport);
            });
        ExcelUtil<CourseApplyExport> util = new ExcelUtil<CourseApplyExport>(CourseApplyExport.class);
        util.exportExcel(response, courseApplyExportList, "选课数据");
    }

    /**
     * 新增课程预约（业务层使用）
     * 选课
     * 需判断course_check状态
     */
    @RequiresPermissions("business:courseApply:add")
    @Log(title = "课程预约管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addCourseApply(@Validated @RequestBody CourseApply courseApply)
    {
        if(courseApplyService.selectByUserIdAndCourseId(courseApply.getUserStudentid(),courseApply.getCourseId())!=0){
            //说明有记录数
            return error("选课失败，已存在选课记录");
        }
        Course course=courseService.selectCourseById(courseApply.getCourseId()) ;
        if(StringUtils.equals(course.getCourseCheck(), ApplyConstants.COURSECHECKNO)){
            //无需审核
            courseApply.setStatus(ApplyConstants.AGREESTATUS);
            courseApply.setRecallStatus(ApplyConstants.RECALLNOT);
        }
        courseApply.setCreateBy(SecurityUtils.getUsername());
        return toAjax(courseApplyService.insertCourseApply(courseApply));
    }
    /**
     * 一级管理员审核课程预约
     * status只能是1 5
     */
    @RequiresPermissions("business:courseApply1:check")
    @Log(title = "课程预约管理", businessType = BusinessType.UPDATE)
    @PutMapping("/check1")
    public AjaxResult check1(@RequestBody List<CourseApply> courseApplyList){
        for(CourseApply temp:courseApplyList) {
            if (StringUtils.equals(temp.getStatus(), ApplyConstants.YIADMINAGREESTATUS)) {
                temp.setStatus(ApplyConstants.AGREESTATUS);
            }
            temp.setUpdateBy(SecurityUtils.getUsername());
            temp.setApplyAdmin1(SecurityUtils.getUserStudentid());
            temp.setApplyAdmin1Name(SecurityUtils.getUsername());
            //修改为不可撤销
            temp.setRecallStatus(ApplyConstants.RECALLNOT);
        }
        courseApplyService.updateCourseApplyList(courseApplyList);
        return success();
    }
    /**
     * 二级管理员审核课程预约
     * status只能是2 5
     */
    @RequiresPermissions("business:courseApply2:check")
    @Log(title = "课程预约管理", businessType = BusinessType.UPDATE)
    @PutMapping("/check2")
    public AjaxResult check2(@RequestBody List<CourseApply> courseApplyList){
        for(CourseApply temp:courseApplyList) {
            //不分大小活动直接通过为2，拒绝为5
            temp.setUpdateBy(SecurityUtils.getUsername());
            temp.setApplyAdmin2(SecurityUtils.getUserStudentid());
            temp.setApplyAdmin2Name(SecurityUtils.getUsername());
            //修改为不可撤销
            temp.setRecallStatus(ApplyConstants.RECALLNOT);
        }
        courseApplyService.updateCourseApplyList(courseApplyList);
        return success();
    }
    /**
     * 用户撤销预约
     * 传递撤销理由和预约单号和status和recallStatus
     * 返回时前端根据recallStatus，为0可点撤销按钮，不可点强行撤销；为1不可点撤销按钮，可点强行撤销按钮；为2什么都不可点
     * 有两个按钮 撤销和强行撤销（扣除5分）
     */
    @PutMapping("/recall")
    public  AjaxResult applyRecall(@RequestBody  CourseApply courseApply){
        //普通撤销
        if(StringUtils.equals(courseApply.getRecallStatus(),ApplyConstants.RECALLCAN)){
            courseApply.setUpdateBy(SecurityUtils.getUsername());
            return  toAjax(courseApplyService.updateCourseApply(courseApply)) ;
        }
        //强行撤销
        if(StringUtils.equals(courseApply.getRecallStatus(),ApplyConstants.RECALLNOT)){
            courseApply.setRecallStatus(ApplyConstants.RECALLCREDIT);
            SysCredit sysCredit=new SysCredit(null,"用户强行撤销课程预约","自己操作",SecurityUtils.getUserStudentid(), Convert.toInt(courseRecallCredit,-2),null,"0","0");
            R<Boolean> booleanR= remoteCreditService.addUserCredit(sysCredit, SecurityConstants.INNER);
            if(R.FAIL == booleanR.getCode()){
                throw new ServiceException(booleanR.getMsg());
            }
            courseApply.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(courseApplyService.updateCourseApply(courseApply));
        }
        return  error("撤销失败或者参数不足");
    }



}
