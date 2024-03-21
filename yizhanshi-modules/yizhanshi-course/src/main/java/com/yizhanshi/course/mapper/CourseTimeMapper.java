package com.yizhanshi.course.mapper;


import com.yizhanshi.course.api.domain.CourseTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTimeMapper {
    /**
     * 根据条件分页查询课程时间列表
     *
     * @param courseTime 课程时间信息
     * @return 课程信息集合信息
     */
    public List<CourseTime> selectCourseTimeList(CourseTime courseTime);
    /**
     * 根据编号查询具体信息
     */
    public CourseTime  selectCourseTimeById(Long courseTimeId);
    /**
     * 根据编号列表返回List集合
     */
    public List<CourseTime>  selectCourseTimeByIds(Long[] courseTimeIds);
    /**
     * 修改课程时间信息
     * @param courseTime
     * @return
     */
    public int updateCourseTime(CourseTime courseTime);
    /**
     * 删除课程信息
     * @param courseTimeIds
     * @return
     */
    public int deleteCourseTime(Long[] courseTimeIds);
    /**
     * 新增课程信息
     * @param courseTime
     * @return
     */
    public int insertCourseTime(CourseTime courseTime);


    /**
     * 根据查询条件，获得当天的课程记录
     */
    public List<CourseTime> selectAllCourse(@Param("placeId") Long placeId, @Param("chooseDay") String chooseDay);

}
