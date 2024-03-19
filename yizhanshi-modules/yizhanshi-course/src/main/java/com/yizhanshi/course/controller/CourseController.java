package com.yizhanshi.course.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.common.core.utils.DateUtils;
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
import com.yizhanshi.course.domain.CourseApply;
import com.yizhanshi.course.domain.vo.AllCourse;
import com.yizhanshi.course.domain.vo.CourseExport;
import com.yizhanshi.course.service.ICourseApplyService;
import com.yizhanshi.course.service.ICourseService;
import com.yizhanshi.place.api.RemotePlaceService;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 业务层——课程信息
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/courseInfo")
public class CourseController extends BaseController {
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ICourseApplyService courseApplyService;


    /**
     * 课程信息-管理员使用
     */
    @RequiresPermissions("business:course:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody Course course) {
        startPage();
        List<Course> list = courseService.selectCourseList(course);
        return getDataTable(list);
    }
    /**
     * 导出课程信息——管理员使用
     * @param response
     * @param course
     */
    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:course:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Course course)
    {
        List<Course> list = courseService.selectCourseList(course);
        List<CourseExport> courseExportList = list.stream()
                .map(courseTemp -> {
                    CourseExport courseExport = new CourseExport();
                    BeanUtils.copyProperties(courseTemp, courseExport);
                    courseExport.setPlaceName(courseTemp.getPlaces().getPlaceName());
                    courseExport.setTeacherName(courseTemp.getTeachers().getTeacherName());
                    return courseExport;
                })
                .collect(Collectors.toList());
        ExcelUtil<CourseExport> util = new ExcelUtil<CourseExport>(CourseExport.class);
        util.exportExcel(response, courseExportList, "课程信息数据");
    }
    /**
     * 根据课程编号获取详细信息
     */
    @RequiresPermissions("business:course:query")
    @GetMapping(value = { "/{courseId}" })
    public AjaxResult getInfo(@PathVariable(value = "courseId") Long courseId){
        return success(courseService.selectCourseById(courseId));
    }

    /**
     * 新增课程信息——管理员使用
     * @param course
     * @return
     */
    @RequiresPermissions("business:course:add")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addCourse(@Validated @RequestBody Course course) {
        //判断时间冲突
        //查询那个场地那天的预约情况
        String str=DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,course.getCourseDay());
        List<Course> dataBaseCourses = courseService.selectAllCourse(course.getPlaceId(),str);
        if(courseService.timeConflict(dataBaseCourses,course)){
            return error("时间冲突!请查看当天课程信息后修改时间");
        }
        //再远程调用该场地服务，判断时间是否冲突
//        List<PlaceApplyTime> placeApplyTimes=new PlaceApplyTime();
//
//        placeApplyTime.setTimeStartId(course.getTimeStartId());
//        placeApplyTime.setTimeEndId(course.getTimeEndId());
//        placeApplyTime.setPlaceId(course.getPlaceId());
//        Map<String,Object> params=new HashMap<>();
//        params.put("chooseDay",str);
//        placeApplyTime.setParams(params);
//        if(courseService.timeConflictByPlace(placeApplyTimes)){
//            return error("与场地预约冲突，请检查场地预约时间");
//        }
        course.setCreateBy(SecurityUtils.getUsername());
        return toAjax(courseService.insertCourse(course));
    }

    /**
     * 修改课程——管理员使用
     * @param course
     * @return
     */
    @RequiresPermissions("business:course:edit")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editCourse(@Validated @RequestBody Course course)
    {
        //判断时间冲突
        //查询那个场地那天的预约情况
        String str=DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,course.getCourseDay());
        List<Course> dataBaseCourses = courseService.selectAllCourse(course.getPlaceId(),str);
        if(courseService.timeConflict(dataBaseCourses,course)){
            return error("时间冲突!请查看当天课程信息后修改时间");
        }
        //再远程调用该场地服务，判断时间是否冲突
//        PlaceApply placeApply=new PlaceApply();
//        placeApply.setTimeStartId(course.getTimeStartId());
//        placeApply.setTimeEndId(course.getTimeEndId());
//        placeApply.setApplyStartTime(course.getCourseStartTime());
//        placeApply.setApplyEndTime(course.getCourseEndTime());
//        placeApply.setPlaceId(course.getPlaceId());
//        Map<String,Object> params=new HashMap<>();
//        params.put("chooseDay",str);
//        placeApply.setParams(params);
//        if(courseService.timeConflictByPlace(placeApply)){
//            return error("与场地预约冲突，请检查场地预约时间");
//        }
        course.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(courseService.updateCourse(course));

    }

    /**
     * 删除课程——管理员使用
     */
    @RequiresPermissions("business:course:remove")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{courseIds}")
    public AjaxResult removeCourse(@PathVariable Long[] courseIds)
    {
        if (!CollectionUtils.isEmpty(courseApplyService.selectByCourseIds(courseIds)))
        {
            return error("存在课程预约记录不可删除");
        }
        return toAjax(courseService.deleteCourse(courseIds));
    }
    /**
     * 查看可预约的课程信息
     * 用于课程预约
     * 前端传递参数为chooseDay place_id
     *
     * @author  hejiale
     */
    @GetMapping("/allCourse")
    public TableDataInfo selectAllCourse(@RequestBody Course course){
        startPage();
        //根据查询条件和分页得到初步的课程信息
        List<Course> list = courseService.selectCourseList(course);
        Long[] courseIdList = list.stream().map(Course::getCourseId).toArray(Long[]::new);
        List<AllCourse> allCourses=new ArrayList<>();
        for(int i=0;i<courseIdList.length;i++){
            //计算选课人数
            int chooseNumber= courseApplyService.selectNumberByCourse(courseIdList[i]);
            Boolean flag;
            if(chooseNumber<list.get(i).getCourseNumber()){
                flag=Boolean.TRUE;
            }else{
                flag=Boolean.FALSE;
            }
            AllCourse allCourse=new AllCourse(list.get(i),chooseNumber,flag);
            allCourses.add(allCourse);
        }
        return getDataTable(allCourses);
    }
    @InnerAuth
    @PostMapping("/timeConflictByCourse")
    public R<Boolean> timeConflictByCourse(@RequestBody Course course){
        String chooseDay= (String) course.getParams().get("chooseDay");
        List<Course> dataBaseCourseApplies = courseService.selectAllCourse(course.getPlaceId(),chooseDay);
        if(courseService.timeConflict(dataBaseCourseApplies,course)){
            return R.fail("时间冲突!请查看当天课程信息后修改时间");
        }
        return R.ok(Boolean.FALSE);

    }
}
