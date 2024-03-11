package com.yizhanshi.course.service.impl;

import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.course.domain.CourseApply;
import com.yizhanshi.course.mapper.CourseApplyMapper;
import com.yizhanshi.course.service.ICourseApplyService;
import com.yizhanshi.place.api.domain.PlaceApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseApplyServiceImpl implements ICourseApplyService {
    @Autowired
    private CourseApplyMapper courseApplyMapper;

    /**
     * 根据课程id查询选课列表
     * @param courseIds
     * @return
     */
    @Override
    public List<CourseApply> selectByCourseIds(Long[] courseIds){
       return  courseApplyMapper.selectByCourseIds(courseIds);
    }
    @Override
    public  int selectNumberByCourse(Long courseId){
        return  courseApplyMapper.selectNumberByCourse(courseId);
    }

    @Override
    public List<CourseApply> selectCourseApplyList(CourseApply courseApply){
        return  courseApplyMapper.selectCourseApplyList(courseApply);
    }
    @Override
    public  CourseApply selectCourseApplyById(Long applyId){
        return  courseApplyMapper.selectCourseApplyById(applyId);
    }
    @Override
    public   int updateCourseApply(CourseApply courseApply){
        return  courseApplyMapper.updateCourseApply(courseApply);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public   int updateCourseApplyList(List<CourseApply> courseApplyList){
        int rows=0;
        for(CourseApply item:courseApplyList){
            int result = courseApplyMapper.updateCourseApply(item);
            if (result == 0) {
                // 当某次更新失败时，抛出异常以触发事务回滚
                throw new ServiceException("更新失败，事务回滚");
            }
            rows += result;
        }
        return rows;
    }
    @Override
    public  int deleteCourseApply(Long[] applyIds){
        return  courseApplyMapper.deleteCourseApply(applyIds);
    }
    @Override
    public int insertCourseApply(CourseApply courseApply){
        return courseApplyMapper.insertCourseApply(courseApply);
    }
    @Override
    public  int selectByUserIdAndCourseId( String userStudentid,  Long courseId){
        return courseApplyMapper.selectByUserIdAndCourseId(userStudentid,courseId);
    }


}
