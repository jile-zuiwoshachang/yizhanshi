package com.yizhanshi.course.controller;


import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;

import com.yizhanshi.course.api.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import  com.yizhanshi.teacher.service.ITeacherService;
import java.util.List;


/**
 * 业务层——老师信息
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
    @Autowired
    private ITeacherService teacherService;


    /**
     * 老师信息-管理员使用
     */
    @RequiresPermissions("business:teacher:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody Teacher teacher) {
        startPage();
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        return getDataTable(list);
    }
    /**
     * 导出老师信息——管理员使用
     * @param response
     * @param teacher
     */
    @Log(title = "老师管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:teacher:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody Teacher teacher)
    {
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        ExcelUtil<Teacher> util = new ExcelUtil<Teacher>(Teacher.class);
        util.exportExcel(response, list, "老师信息数据");
    }
    /**
     * 根据老师编号获取详细信息
     */
    @RequiresPermissions("business:teacher:query")
    @GetMapping(value = { "/{teacherId}" })
    public AjaxResult getInfo(@PathVariable(value = "teacherId") Long teacherId){
        return success(teacherService.selectTeacherById(teacherId));
    }

    /**
     * 新增老师信息——管理员使用
     * @param teacher
     * @return
     */
    @RequiresPermissions("business:teacher:add")
    @Log(title = "老师管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addTeacher(@Validated @RequestBody Teacher teacher) {
        teacher.setCreateBy(SecurityUtils.getUsername());
        return toAjax(teacherService.insertTeacher(teacher));
    }

    /**
     * 修改老师——管理员使用
     * @param teacher
     * @return
     */
    @RequiresPermissions("business:teacher:edit")
    @Log(title = "老师管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editTeacher(@Validated @RequestBody Teacher teacher)
    {
        teacher.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(teacherService.updateTeacher(teacher));

    }

    /**
     * 删除老师——管理员使用
     */
    @RequiresPermissions("business:teacher:remove")
    @Log(title = "老师管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{teacherIds}")
    public AjaxResult removeTeacher(@PathVariable Long[] teacherIds) {
        return toAjax(teacherService.deleteTeacher(teacherIds));
    }
}
