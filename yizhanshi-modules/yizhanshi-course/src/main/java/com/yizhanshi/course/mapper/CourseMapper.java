package com.yizhanshi.course.mapper;

import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.domain.CourseApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    /**
     * 根据条件分页查询课程列表
     *
     * @param course 课程信息
     * @return 场课程信息集合信息
     */
    public List<Course> selectCourseList(Course course);
    /**
     * 根据课程id查询
     *
     * @param courseId 课程id
     * @return 课程信息信息
     */
    public Course selectCourseById(Long  courseId);
    /**
     * 新增课程信息
     * @param course
     * @return
     */
    public int insertCourse(Course course);
    /**
     * 修改课程信息
     * @param course
     * @return
     */
    public int updateCourse(Course course);

    /**
     * 删除课程信息
     * @param courseIds
     * @return
     */
    public int deleteCourse(Long[] courseIds);
    /**
     * 根据查询条件，获得当天的课程预约记录
     */
    public List<Course> selectAllCourse(@Param("placeId") Long placeId, @Param("chooseDay") String chooseDay);
}
