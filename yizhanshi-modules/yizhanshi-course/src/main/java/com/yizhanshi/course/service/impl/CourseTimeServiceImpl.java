package com.yizhanshi.course.service.impl;

import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.domain.CourseTime;
import com.yizhanshi.course.domain.CourseTimeRelated;
import com.yizhanshi.course.mapper.CourseTimeMapper;
import com.yizhanshi.course.mapper.CourseTimeRelatedMapper;
import com.yizhanshi.course.service.ICourseTimeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.OnError;
import java.util.List;
import java.util.Objects;

@Service
public class CourseTimeServiceImpl implements ICourseTimeService {
    @Autowired
    private CourseTimeMapper courseTimeMapper;
    @Autowired
    private CourseTimeRelatedMapper courseTimeRelatedMapper;
    /**
     * 根据条件分页查询课程时间时间列表
     *
     * @param courseTime 课程时间时间信息
     * @return 课程时间信息集合信息
     */
    @Override
    public List<CourseTime> selectCourseTimeList(CourseTime courseTime){
        return courseTimeMapper.selectCourseTimeList(courseTime);
    }
    /**
     * 根据编号查询具体信息
     */
    @Override
    public CourseTime  selectCourseTimeById(Long courseTimeId){
        return courseTimeMapper.selectCourseTimeById(courseTimeId);
    }
    /**
     * 根据编号列表返回List集合
     */
    @Override
    public List<CourseTime>  selectCourseTimeByIds(Long[] courseTimeIds){
        return courseTimeMapper.selectCourseTimeByIds(courseTimeIds);
    }

    /**
     * 修改课程时间时间信息
     * @param courseTime
     * @return
     */
    @Override
    public int updateCourseTime(CourseTime courseTime){
        return courseTimeMapper.updateCourseTime(courseTime);
    }
    /**
     * 删除课程时间信息
     * @param courseTimeIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCourseTime(Long[] courseTimeIds){
        courseTimeMapper.deleteCourseTime(courseTimeIds);
        for(Long courseTimeId:courseTimeIds){
            courseTimeRelatedMapper.deleteCTRelatedByCourseTiemId(courseTimeId);
        }
    }
    /**
     * 新增课程时间信息
     * @param courseTime
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCourseTime(CourseTime courseTime){
        Long courseId= courseTime.getCourseId();
        //不冲突则添加关联
        courseTimeMapper.insertCourseTime(courseTime);
        Long courseTimeId=courseTime.getCourseTimeId();
        CourseTimeRelated ctRelated = new CourseTimeRelated();
        ctRelated.setCourseId(courseId);
        ctRelated.setCourseTimeId(courseTimeId);
        // 插入关联记录
        courseTimeRelatedMapper.insertCTRelated(ctRelated);
    }

    /**
     * 根据查询条件，获得当天的课程时间记录
     */
    @Override
    public List<CourseTime> selectAllCourse( Long placeId,String chooseDay){
        return courseTimeMapper.selectAllCourse(placeId,chooseDay);
    }
    /**
     * 判断冲突
     */
    @Override
    public  Boolean timeConflict(List<CourseTime> dataBase, CourseTime newCourseTime){
        Boolean flag=Boolean.FALSE;
        for(int i=0;i<dataBase.size();i++){
            CourseTime oldCourseTime= dataBase.get(i);
            Long startOld=oldCourseTime.getTimeStartId();
            Long endOld=oldCourseTime.getTimeEndId();
            Long startNew= newCourseTime.getTimeStartId();
            Long endNew=newCourseTime.getTimeEndId();
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
}
