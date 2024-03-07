package com.yizhanshi.teacher.service;


import com.yizhanshi.course.api.domain.Teacher;
import com.yizhanshi.place.api.domain.PlaceApply;

import java.util.List;

public interface ITeacherService {
    /**
     * 根据条件分页查询老师列表
     *
     * @param teacher 老师信息
     * @return 老师信息集合信息
     */
    public List<Teacher> selectTeacherList(Teacher teacher);

    /**
     * 通过老师ID查询老师
     *
     * @param teacherId  老师ID
     * @return 老师对象信息
     */
    public Teacher selectTeacherById(Long teacherId);
    /**
     * 增加老师信息
     * @param teacher
     * @return 结果
     */
    public int insertTeacher(Teacher teacher);
    /**
     * 修改老师信息
     *
     * @param teacher 老师信息
     * @return 结果
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 删除老师信息
     *
     * @param teacherIds 老师id
     * @return 结果
     */
    public int deleteTeacher(Long[] teacherIds);

}
