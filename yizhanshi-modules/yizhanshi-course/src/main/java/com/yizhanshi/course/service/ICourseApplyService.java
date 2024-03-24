package com.yizhanshi.course.service;

import com.yizhanshi.course.domain.CourseApply;
import com.yizhanshi.place.api.domain.PlaceApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICourseApplyService {
    /**
     * 根据课程id查询选课列表
     * @param courseIds
     * @return
     */
    public List<CourseApply> selectByCourseIds(Long[] courseIds);
    /**
     * 查询课程id所在课程的选课人数
     */
    public int selectNumberByCourse(Long courseId);

    /**
     * 查询课程预约单列表
     * @param courseApply
     * @return
     */
    public List<CourseApply> selectCourseApplyList(CourseApply courseApply);

    /**
     * 根据编号查询具体信息
     */
    public CourseApply selectCourseApplyById(Long applyId);
    /**
     * 修改课程预约信息
     */
    public  int updateCourseApply(CourseApply courseApply);
    /**
     * 多选修改课程预约信息
     */
    public  void updateCourseApplyList(List<CourseApply> courseApplyList);
    /**
     * 删除选课信息
     */
    public int deleteCourseApply(Long[] applyIds);
    /**
     * 新增选课信息
     */
    public int insertCourseApply(CourseApply courseApply);
    /**
     * 查询是否选过课
     */
    public int selectByUserIdAndCourseId(String userStudentid, Long courseId);

}
