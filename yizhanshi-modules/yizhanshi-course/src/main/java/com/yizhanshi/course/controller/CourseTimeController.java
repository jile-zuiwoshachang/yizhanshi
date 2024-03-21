package com.yizhanshi.course.controller;

import com.yizhanshi.common.core.utils.DateUtils;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.course.api.domain.CourseTime;
import com.yizhanshi.course.service.ICourseApplyService;
import com.yizhanshi.course.service.ICourseService;
import com.yizhanshi.course.service.ICourseTimeRelatedService;
import com.yizhanshi.course.service.ICourseTimeService;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
/**
 * 课程时间信息 业务处理
 *
 * @author hejiale
 */
@RefreshScope
@RestController
@RequestMapping("/courseTime")
public class CourseTimeController extends BaseController {
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ICourseApplyService courseApplyService;
    @Autowired
    private ICourseTimeRelatedService courseTimeRelatedService;
    
    @Autowired
    private ICourseTimeService courseTimeService;
    /**
     * 课程时间信息——管理员使用
     */
    @RequiresPermissions("business:courseTime:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody CourseTime courseTime) {
        startPage();
        List<CourseTime> list = courseTimeService.selectCourseTimeList(courseTime);
        return getDataTable(list);
    }

    /**
     * 导出课程时间信息——管理员使用
     * @param response
     * @param placeAppplyTime
     */
    @Log(title = "课程时间管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:courseTime:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody CourseTime placeAppplyTime)
    {
        List<CourseTime> list = courseTimeService.selectCourseTimeList(placeAppplyTime);
        ExcelUtil<CourseTime> util = new ExcelUtil<CourseTime>(CourseTime.class);
        util.exportExcel(response, list, "课程时间信息数据");
    }
    /**
     * 根据课程时间编号获取详细信息——管理员使用
     */
    @RequiresPermissions("business:courseTime:query")
    @GetMapping(value = { "/{courseTimeId}" })
    public AjaxResult getInfo(@PathVariable(value = "courseTimeId") Long courseTimeId){
        return success(courseTimeService.selectCourseTimeById(courseTimeId));
    }

    /**
     * 新增课程时间信息——管理员使用
     * 比如这个课程还想继续新增时间
     * 是后期修改用，不是第一次课程时间使用
     * @param courseTime
     * @return
     */
    @RequiresPermissions("business:courseTime:add")
    @Log(title = "课程时间管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody CourseTime courseTime) {
        //先判断冲突
        String str= DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,courseTime.getCourseDay());
        List<CourseTime> dataBasePlaceTimeApplies = courseTimeService.selectAllCourse(courseTime.getPlaceId(), str);
        //先判断所选择天数的所在场地第一天的情况，然后循环判断
        if(courseTimeService.timeConflict(dataBasePlaceTimeApplies,courseTime)){
            return error("时间冲突!请查看当天课程时间信息后修改时间");
        }
        //再远程调用场地服务，判断时间是否冲突
        //单个对象转化为列表方法
        if(timeConflict(Collections.singletonList(courseTime), str)){
            return error("时间冲突！请查看当天场地预约信息后修改时间");
        }
        //不冲突则新增
        courseTime.setCreateBy(SecurityUtils.getUsername());
        courseTimeService.insertCourseTime(courseTime);
        return success();
    }
    private Boolean timeConflict(List<CourseTime> courseTimes, String str) {
        //再远程调用场地服务，判断时间是否冲突
        List<PlaceApplyTime> placeApplyTimes = new ArrayList<>();
        for (CourseTime courseTime : courseTimes) {
            PlaceApplyTime placeApplyTime=new PlaceApplyTime();
            placeApplyTime.setTimeStartId(courseTime.getTimeStartId());
            placeApplyTime.setTimeEndId(courseTime.getTimeEndId());
            placeApplyTime.setPlaceId(courseTime.getPlaceId());
            Map<String, Object> params = new HashMap<>();
            params.put("chooseDay", str);
            placeApplyTime.setParams(params);
        }
        return courseService.timeConflictByPlace(placeApplyTimes);
    }

    /**
     * 删除课程时间信息——管理员使用
     */
    @RequiresPermissions("business:placeTime:remove")
    @Log(title = "课程时间管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{courseTimeIds}")
    public AjaxResult remove(@PathVariable Long[] courseTimeIds)
    {
        courseTimeService.deleteCourseTime(courseTimeIds);
        return success();
    }
}
