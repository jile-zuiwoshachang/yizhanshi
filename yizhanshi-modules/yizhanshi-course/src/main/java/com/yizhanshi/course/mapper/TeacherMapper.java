package com.yizhanshi.course.mapper;



import com.yizhanshi.course.api.domain.Teacher;

import java.util.List;

public interface TeacherMapper {
    /**
     * 根据条件分页查询老师列表
     *
     * @param teacher 老师信息
     * @return 场老师信息集合信息
     */
    public List<Teacher> selectTeacherList(Teacher teacher);
    /**
     * 根据老师id查询
     *
     * @param teacherId 老师id
     * @return 老师信息信息
     */
    public Teacher selectTeacherById(Long  teacherId);
    /**
     * 新增老师信息
     * @param teacher
     * @return
     */
    public int insertTeacher(Teacher teacher);
    /**
     * 修改老师信息
     * @param teacher
     * @return
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 删除老师信息
     * @param teacherIds
     * @return
     */
    public int deleteTeacher(Long[] teacherIds);
}
