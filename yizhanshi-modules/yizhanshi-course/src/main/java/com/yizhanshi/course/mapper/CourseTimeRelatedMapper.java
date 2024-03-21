package com.yizhanshi.course.mapper;


import com.yizhanshi.course.domain.CourseTimeRelated;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 场地预约与时间关联表 数据层
 *
 * @author  hejiale
 */
public interface CourseTimeRelatedMapper {
    /**
     * 获得课程id的所有时间
     */
    public List<Long> selectCourseTimeIdsByCourseId(Long courseId);
    /**
     * 获得课程时间id的获得课程id
     */
    public Long selectCourseIdByCourseTimeId(Long courseTimeId);
    /**
     * 根据课程时间id查询对应的课程
     */
    public List<Long> selectCourseIdsByCourseTimeIds(Long[] courseTimeIds);
    /**
     * 批量新增课程和时间关联信息
     *
     * @param courseTimeRelated 课程和时间关联列表
     * @return 结果
     */
    public int insertCTRelated(CourseTimeRelated courseTimeRelated);


    /**
     * 通过预约时间单id删除关联
     *
     * @param courseId 需要删除的课程id
     * @return 结果
     */
    public int deleteCTRelatedByCourseId(Long courseId);
    /**
     * 通过时间id删除关联
     *
     * @param courseTimeId 需要删除的时间id
     * @return 结果
     */
    public int deleteCTRelatedByCourseTiemId(Long courseTimeId);
    /**
     * 删除课程与时间关联
     * 删除单个
     * @param courseTimeRelated 课程与时间关联信息
     * @return 结果
     */
    public int deleteCTRelated(CourseTimeRelated courseTimeRelated);

    /**
     * 批量删除课程和时间关联信息
     *
     * @param courseId 课程单id
     * @param courseTimeIds 时间ids
     * @return 结果
     */
    public int deleteCTRelateds(@Param("courseId") Long courseId, @Param("courseTimeIds") Long[] courseTimeIds);

}
