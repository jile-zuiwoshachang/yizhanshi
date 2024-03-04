package com.yizhanshi.course.service.impl;

import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.course.domain.Course;
import com.yizhanshi.course.mapper.CourseMapper;
import com.yizhanshi.course.service.ICourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
    @Autowired
    private CourseMapper courseMapper;

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
    public int insertCourse(Course course){
        return courseMapper.insertCourse(course);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCourse(Course course){
        return courseMapper.updateCourse(course);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int  deleteCourse(Long[] courseIds){
        return courseMapper.deleteCourse(courseIds);
    }
    
}
