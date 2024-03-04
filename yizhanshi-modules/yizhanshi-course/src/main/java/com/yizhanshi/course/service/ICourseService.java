package com.yizhanshi.course.service;

import com.yizhanshi.course.domain.Course;

import java.util.List;

public interface ICourseService {
    /**
     * 根据条件分页查询课程列表
     *
     * @param course 课程信息
     * @return 课程信息集合信息
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 通过课程ID查询课程
     *
     * @param courseId  课程ID
     * @return 课程对象信息
     */
    public Course selectCourseById(Long courseId);
    /**
     * 增加课程信息
     * @param course
     * @return 结果
     */
    public int insertCourse(Course course);
    /**
     * 修改课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 删除课程信息
     *
     * @param courseIds 课程id
     * @return 结果
     */
    public int deleteCourse(Long[] courseIds);
}
