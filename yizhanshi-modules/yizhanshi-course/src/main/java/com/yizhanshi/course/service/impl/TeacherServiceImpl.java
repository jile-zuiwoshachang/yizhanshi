package com.yizhanshi.course.service.impl;


import com.yizhanshi.course.api.domain.Teacher;
import com.yizhanshi.course.mapper.TeacherMapper;
import com.yizhanshi.teacher.service.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> selectTeacherList(Teacher teacher)
    {
        return teacherMapper.selectTeacherList(teacher);
    }
    @Override
    public Teacher selectTeacherById(Long teacherId)
    {
        return teacherMapper.selectTeacherById(teacherId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertTeacher(Teacher teacher){
        return teacherMapper.insertTeacher(teacher);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateTeacher(Teacher teacher){
        return teacherMapper.updateTeacher(teacher);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int  deleteTeacher(Long[] teacherIds){
        return teacherMapper.deleteTeacher(teacherIds);
    }
}
