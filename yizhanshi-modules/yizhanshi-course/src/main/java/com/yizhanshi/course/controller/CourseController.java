package com.yizhanshi.course.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
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
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.domain.CourseTime;
import com.yizhanshi.course.domain.CourseApply;
import com.yizhanshi.course.domain.constants.ApplyConstants;
import com.yizhanshi.course.domain.vo.AllCourse;
import com.yizhanshi.course.domain.vo.CourseExport;
import com.yizhanshi.course.service.ICourseApplyService;
import com.yizhanshi.course.service.ICourseService;
import com.yizhanshi.course.service.ICourseTimeRelatedService;
import com.yizhanshi.course.service.ICourseTimeService;
import com.yizhanshi.place.api.RemotePlaceService;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import io.netty.handler.ssl.PemPrivateKey;
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
    private ICourseTimeService courseTimeService;
    @Autowired
    private ICourseTimeRelatedService courseTimeRelatedService;
    @Autowired
    private ICourseApplyService courseApplyService;
    @Autowired
    private RemotePlaceService remotePlaceService;


    /**
     * 课程信息-管理员使用
     */
    @RequiresPermissions("business:course:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody Course course) {
        startPage();
        List<Course> courseList=courseService.selectCourseList(course);
        for(Course course1:courseList){
            Long[] courseTimeIds = courseTimeRelatedService.selectCourseTimeIdsByCourseId(course1.getCourseId())
                    .stream().toArray(Long[]::new);
            List<CourseTime> courseTimeList=courseTimeService.selectCourseTimeByIds(courseTimeIds);
            course.setCourseTimes(courseTimeList);
        }
        return getDataTable(courseList);
    }

    /**
     * 导出课程信息——管理员使用
     *
     * @param response
     * @param course
     */
    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:course:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Course course) {
        List<Course> list = courseService.selectCourseList(course);
        List<CourseExport> courseExportList=new ArrayList<>();
        list.forEach(courseTemp -> {
            Long[] courseTimeIds = courseTimeRelatedService.selectCourseTimeIdsByCourseId(courseTemp.getCourseId())
                    .stream()
                    .toArray(Long[]::new);
            List<CourseTime> courseTimeList = courseTimeService.selectCourseTimeByIds(courseTimeIds);
            courseTimeList.forEach(courseTime -> {
                CourseExport courseExport = new CourseExport();
                BeanUtils.copyProperties(courseTemp,courseExport);
                //不可再使用BeanUtils赋予courseTime
                courseExport.setCourseDay(courseTime.getCourseDay());
                courseExport.setCourseStartTime(courseTime.getCourseStartTime());
                courseExport.setCourseEndTime(courseTime.getCourseEndTime());
                courseExport.setPlaceName(courseTime.getPlace().getPlaceName());
                courseExport.setPlaceCampus(courseTime.getPlace().getPlaceCampus());
                courseExport.setTeacherName(courseTemp.getTeacher().getTeacherName());
                courseExportList.add(courseExport);
            });
        });
        ExcelUtil<CourseExport> util = new ExcelUtil<CourseExport>(CourseExport.class);
        util.exportExcel(response, courseExportList, "课程信息数据");
    }

    /**
     * 根据课程编号获取详细信息
     */
    @RequiresPermissions("business:course:query")
    @GetMapping(value = {"/{courseId}"})
    public AjaxResult getInfo(@PathVariable(value = "courseId") Long courseId) {
        Course course=courseService.selectCourseById(courseId);
        Long[] courseTimeIds = courseTimeRelatedService.selectCourseTimeIdsByCourseId(courseId)
                .stream().toArray(Long[]::new);
        List<CourseTime> courseTimeList=courseTimeService.selectCourseTimeByIds(courseTimeIds);
        course.setCourseTimes(courseTimeList);
        return success(course);
    }

    /**
     * 新增课程信息——管理员使用
     * 排课
     *
     * @param course
     * @return
     */
    @RequiresPermissions("business:course:add")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addCourse(@Validated @RequestBody Course course) {
        //判断时间冲突
        //查询那个场地那天的预约情况
        if(CollectionUtils.isEmpty(course.getCourseTimes())){
            throw new ServiceException("时间不能为空");
        }
        for (CourseTime courseTime : course.getCourseTimes()) {
            String str = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, courseTime.getCourseDay());
            List<CourseTime> dataBaseCourseTimeApplies = courseTimeService.selectAllCourse(courseTime.getPlaceId(), str);
            //先判断所选择天数的所在场地第一天的情况，然后循环判断
            if (courseTimeService.timeConflict(dataBaseCourseTimeApplies, courseTime)) {
                return error("时间冲突!请查看当天课程信息后修改时间");
            }
            //远程调用场地判断
            if(timeConflict(Collections.singletonList(courseTime), str)){
                return error("时间冲突！请查看当天场地预约信息后修改时间");
            }
        }
        course.setCreateBy(SecurityUtils.getUsername());
        courseService.insertCourse(course);
        return success();

    }

    private Boolean timeConflict(List<CourseTime> courseTimes,String str) {
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
     * 修改课程——管理员使用
     * @param course
     * @return
     */
    @RequiresPermissions("business:course:edit")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editCourse(@Validated @RequestBody Course course) {
        //不可修改时间，只能删再加
        course.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(courseService.updateCourse(course));

    }

    /**
     * 删除课程——管理员使用
     */
    @RequiresPermissions("business:course:remove")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{courseIds}")
    public AjaxResult removeCourse(@PathVariable Long[] courseIds) {
      courseService.deleteCourse(courseIds);
      return success();
    }

    /**
     * 查看可预约的课程信息
     * 用于课程预约
     * 前端传递参数为 place
     *
     * @author hejiale
     */
    @GetMapping("/allCourse")
    public TableDataInfo selectAllCourse(@RequestBody Place place) {
        startPage();
        //根据查询条件和分页得到初步的课程信息
        List<Place> list = courseService.selectPlaceList(place);
        if (CollectionUtils.isEmpty(list)) {
            throw new ServiceException("场地列表为空");
        }
        Date chooseDay = Optional.ofNullable(place.getParams().get("chooseDay"))
                .map(DateUtils::parseDate)
                .orElse(new Date());
        //获得每个placeid的chooseDay那天的课程时间记录
        //AI优化： List<List<T>>，可以使用flatMap将它平铺成List<T>。
        /**
         *placeId -> getCourseInfoByPlaceAndDate(place, chooseDay).stream() 这段代码首先会针对每个 place 调用方法，该方法返回一个 List<AllCourse>。
         * 接着，.stream() 将这个列表转换成一个流。会得到一个形如 Stream<List<AllCourse>> 的结果
         *
         */
        List<AllCourse> allCourses = list.stream()
                .map(placeItem -> getCourseInfoByPlaceAndDate(placeItem, chooseDay))
                .collect(Collectors.toList());
        return getDataTable(allCourses);
    }

    private AllCourse getCourseInfoByPlaceAndDate(Place place, Date chooseDay) {
        //selectAllCourse仅能得到课程时间和场地信息
        List<CourseTime> courseTimeList = courseTimeService.selectAllCourse(place.getPlaceId(), DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, chooseDay));
        //有课程时间则反查询课程，得到这个place下这天的所有课程时间列表，但是不知道课程是谁，要开始填充课程信息和选课人数

        for (CourseTime courseTime : courseTimeList) {
            // 对于每个CourseTime，找到对应的课程ID列表
            Long courseId = courseTimeRelatedService.selectCourseIdByCourseTimeId(courseTime.getCourseTimeId());
            // 对于每个课程ID，获取课程信息和选课人数
            Course course = courseService.selectCourseById(courseId);
            int chooseNumber = courseApplyService.selectNumberByCourse(courseId);
            Boolean flag = chooseNumber < course.getCourseNumber();
            // 现在我们有了Place, CourseTime, Course, 以及chooseNumber，flag, 可以创建AllCourse对象
            courseTime.setCourse(course);
            course.setChooseNumber(chooseNumber);
            course.setFlag(flag);
        }
        AllCourse allCourse = new AllCourse(place, courseTimeList);
        return allCourse;
    }

    @InnerAuth
    @PostMapping("/timeConflictByCourse")
    public R<Boolean> timeConflictByCourse(@RequestBody List<CourseTime> courseTimes) {
        //str为日期字符串
        for (CourseTime courseTime : courseTimes) {
            String str = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, courseTime.getCourseDay());
            List<CourseTime> dataBasePlaceTimeApplies = courseTimeService.selectAllCourse(courseTime.getPlaceId(), str);
            //先判断所选择天数的所在场地第一天的情况，然后循环判断
            if (courseTimeService.timeConflict(dataBasePlaceTimeApplies, courseTime)) {
                return R.fail("时间冲突!请查看当天场地课程信息后修改时间");
            }
        }
        return R.ok(Boolean.FALSE);

    }
}
