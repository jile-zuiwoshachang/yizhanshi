package com.yizhanshi.course.service;

import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.place.api.domain.Place;
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
    public void insertCourse(Course course);
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
    public void deleteCourse(Long[] courseIds);


    /**
     * 判断与场地的冲突
     */
    public Boolean  timeConflictByPlace(List<PlaceApplyTime> placeApplyTimes);
    /**
     * 远程调用场地查询
     */
    public List<Place> selectPlaceList(Place place);

}
