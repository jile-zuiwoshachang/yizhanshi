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
import com.yizhanshi.course.domain.Course;
import com.yizhanshi.course.domain.CourseApply;
import com.yizhanshi.course.domain.vo.AllCourse;
import com.yizhanshi.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        ExcelUtil<Course> util = new ExcelUtil<Course>(Course.class);
        util.exportExcel(response, list, "课程信息数据");
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
            return error("存在课程申请记录不可删除");
        }
        return toAjax(courseService.deleteCourse(courseIds));
    }
    /**
     * 查看可预约的课程信息
     * 用于课程申请
     * 前端传递参数为chooseDay
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
            //获得每个courseid的chooseDay那天的课程申请记录
            Date chooseDay= DateUtils.parseDate(course.getParams().get("chooseDay"));
            //为转化加上保险
            List<CourseApply> courseApplyList= courseApplyService.selectAllCourse(courseIdList[i],  DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,chooseDay));
            AllCourse allCourse=new AllCourse(list.get(i),courseApplyList);
            allCourses.add(allCourse);
        }
        return getDataTable(allCourses);
    }
}
