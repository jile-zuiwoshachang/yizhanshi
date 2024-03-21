package com.yizhanshi.course.service.impl;

import com.yizhanshi.course.domain.CourseTimeRelated;
import com.yizhanshi.course.mapper.CourseTimeMapper;
import com.yizhanshi.course.mapper.CourseTimeRelatedMapper;
import com.yizhanshi.course.service.ICourseTimeRelatedService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseTimeRelatedServiceImpl implements ICourseTimeRelatedService {
    @Autowired
    private CourseTimeRelatedMapper courseTimeRelatedMapper;
  
    
    
    /**
     * 获得课程id的所有时间
     */
    @Override
    public List<Long> selectCourseTimeIdsByCourseId(Long courseId){
        return courseTimeRelatedMapper.selectCourseTimeIdsByCourseId(courseId);
    }
    @Override
    public Long selectCourseIdByCourseTimeId(Long courseTimeId){
        return courseTimeRelatedMapper.selectCourseIdByCourseTimeId(courseTimeId);
    }
    /**
     * 根据课程时间id查询对应的课程
     */
    @Override
    public List<Long> selectCourseIdsByCourseTimeIds(Long[] courseTimeIds){
        return courseTimeRelatedMapper.selectCourseIdsByCourseTimeIds(courseTimeIds);
    }
    /**
     * 批量新增课程和时间关联信息
     *
     * @param courseTimeRelated 课程和时间关联列表
     * @return 结果
     */
    public int insertCTRelated(CourseTimeRelated courseTimeRelated){
        return courseTimeRelatedMapper.insertCTRelated(courseTimeRelated);
    }


    /**
     * 通过预约时间单id删除关联
     *
     * @param courseId 需要删除的课程id
     * @return 结果
     */
    public int deleteCTRelatedByCourseId(Long courseId){
        return courseTimeRelatedMapper.deleteCTRelatedByCourseId(courseId);
    }
    
    /**
     * 通过时间id删除关联
     *
     * @param courseTimeId 需要删除的时间id
     * @return 结果
     */
    public int deleteCTRelatedByCourseTiemId(Long courseTimeId){
        return courseTimeRelatedMapper.deleteCTRelatedByCourseTiemId(courseTimeId);
    }
    /**
     * 删除课程与时间关联
     * 删除单个
     * @param courseTimeRelated 课程与时间关联信息
     * @return 结果
     */
    public int deleteCTRelated(CourseTimeRelated courseTimeRelated){
        return courseTimeRelatedMapper.deleteCTRelated(courseTimeRelated);
    }

    /**
     * 批量删除课程和时间关联信息
     *
     * @param courseId 课程单id
     * @param courseTimeIds 时间ids
     * @return 结果
     */
    public int deleteCTRelateds(Long courseId, Long[] courseTimeIds){
        return courseTimeRelatedMapper.deleteCTRelateds(courseId,courseTimeIds);
    }
}
