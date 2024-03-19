package com.yizhanshi.course.service;

import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;

import java.util.List;
import java.util.Map;

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
    /**
     * 判断时间冲突
     * 和场地预约服务的timeConflict原理相同
     */
    public Boolean timeConflict(List<Course> dataBase, Course newCourse);
    /**
     * 查询选择的日期且是这个场地的课程信息
     * @param placeId
     * @param chooseDay
     * @return
     */
    public List<Course> selectAllCourse(Long placeId, String chooseDay);
    /**
     * 判断与场地的冲突
     */
    public Boolean  timeConflictByPlace(List<PlaceApplyTime> placeApplyTimes);
}
