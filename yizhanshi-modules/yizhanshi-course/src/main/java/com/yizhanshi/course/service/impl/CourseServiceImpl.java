package com.yizhanshi.course.service.impl;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.domain.CourseTime;
import com.yizhanshi.course.domain.CourseTimeRelated;
import com.yizhanshi.course.mapper.CourseMapper;
import com.yizhanshi.course.mapper.CourseTimeMapper;
import com.yizhanshi.course.mapper.CourseTimeRelatedMapper;
import com.yizhanshi.course.service.ICourseService;
import com.yizhanshi.place.api.RemotePlaceService;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private CourseTimeMapper courseTimeMapper;
    @Autowired
    private CourseTimeRelatedMapper courseTimeRelatedMapper;
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
    @Transactional(rollbackFor = Exception.class)
    public void insertCourse(Course course){
       courseMapper.insertCourse(course);
        //不冲突则添加关联
        for(CourseTime courseTime:course.getCourseTimes()){
            courseTimeMapper.insertCourseTime(courseTime);
            Long courseTimeId=courseTime.getCourseTimeId();
            Long courseId= course.getCourseId();
            CourseTimeRelated ctRelated = new CourseTimeRelated();
            ctRelated.setCourseId(courseId);
            ctRelated.setCourseTimeId(courseTimeId);
            // 插入关联记录
            courseTimeRelatedMapper.insertCTRelated(ctRelated);
        }
    }
    @Override
    public int updateCourse(Course course){
        return courseMapper.updateCourse(course);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void  deleteCourse(Long[] courseIds){
        courseMapper.deleteCourse(courseIds);
        for(Long courseId:courseIds){
            Long[] courseTimeIds=courseTimeRelatedMapper.selectCourseTimeIdsByCourseId(courseId).stream().toArray(Long[]::new);
            courseTimeRelatedMapper.deleteCTRelatedByCourseId(courseId);
            courseTimeMapper.deleteCourseTime(courseTimeIds);
        }
    }

    @Override
    public Boolean  timeConflictByPlace(List<PlaceApplyTime> placeApplyTimes){
        R<Boolean> booleanR=remotePlaceService.timeConflictByPlace(placeApplyTimes, SecurityConstants.INNER);
            if(R.FAIL == booleanR.getCode()){
            throw new  ServiceException(booleanR.getMsg());
        }
        return  booleanR.getData();

    }
    @Override
    public List<Place> selectPlaceList(Place place){
        R<List<Place>> listR = remotePlaceService.selectList(place, SecurityConstants.INNER);
        if(R.FAIL == listR.getCode()){
            throw new ServiceException(listR.getMsg());
        }
        return listR.getData();
    }

    
}
