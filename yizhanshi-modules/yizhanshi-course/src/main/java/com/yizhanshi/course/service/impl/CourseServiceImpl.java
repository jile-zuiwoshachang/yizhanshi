package com.yizhanshi.course.service.impl;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.mapper.CourseMapper;
import com.yizhanshi.course.service.ICourseService;
import com.yizhanshi.place.api.RemotePlaceService;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CourseServiceImpl implements ICourseService {
    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private RemotePlaceService remotePlaceService;

    @Override
    public List<Course> selectCourseList(Course course)
    {
        return courseMapper.selectCourseList(course);
    }
    @Override
    public Course selectCourseById(Long courseId)
    {
        return courseMapper.selectCourseById(courseId);
    }

    @Override
    public int insertCourse(Course course){
        return courseMapper.insertCourse(course);
    }
    @Override
    public int updateCourse(Course course){
        return courseMapper.updateCourse(course);
    }
    @Override
    public int  deleteCourse(Long[] courseIds){
        return courseMapper.deleteCourse(courseIds);
    }
    @Override
    public  Boolean timeConflict(List<Course> dataBase, Course newCourse){
        Boolean flag=Boolean.FALSE;
        for(int i=0;i<dataBase.size();i++){
            Course oldCourse= dataBase.get(i);
            if(newCourse.getCourseId()!=null && Objects.equals(oldCourse.getCourseId(), newCourse.getCourseId())){
                //跳出此次循环
                break;
            }
            Long startOld=oldCourse.getTimeStartId();
            Long endOld=oldCourse.getTimeEndId();
            Long startNew= newCourse.getTimeStartId();
            Long endNew=newCourse.getTimeEndId();
            //比较id大小
            if (startOld >= endNew) {
                flag=Boolean.FALSE;
            } else if (endOld <= startNew) {
                flag=Boolean.FALSE;
            } else {
                //冲突直接返回
               return  Boolean.TRUE;
            }
        }
        return  flag;
    }
    @Override
    public List<Course> selectAllCourse(Long placeId, String chooseDay){
        return  courseMapper.selectAllCourse(placeId,chooseDay);
    }
    @Override
    public Boolean  timeConflictByPlace(List<PlaceApplyTime> placeApplyTimes){
        R<Boolean> booleanR=remotePlaceService.timeConflictByPlace(placeApplyTimes, SecurityConstants.INNER);
            if(R.FAIL == booleanR.getCode()){
            throw new  ServiceException(booleanR.getMsg());
        }
        return  booleanR.getData();

    }
    
}
