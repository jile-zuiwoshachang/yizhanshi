package com.yizhanshi.course.mapper;

import com.yizhanshi.course.domain.CourseApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseApplyMapper {
    /**
     * 根据条件分页查询课程申请列表
     *
     * @param courseApply 课程申请信息
     * @return 课程申请信息集合信息
     */
    public List<CourseApply> selectCourseApplyList(CourseApply courseApply);
    /**
     * 根据编号查询具体信息
     */
    public CourseApply  selectCourseApplyById(Long applyId);
    /**
     * 修改课程申请息
     * @param courseApply
     * @return
     */
    public int updateCourseApply(CourseApply courseApply);
    /**
     * 删除课程申请息
     * @param applyIds
     * @return
     */
    public int deleteCourseApply(Long[] applyIds);
    /**
     * 新增课程申请息
     * @param courseApply
     * @return
     */
    public int insertCourseApply(CourseApply courseApply);
    /**
     * 查看该课程有多少个被预约记录
     */
    public List<CourseApply> selectByCourseIds(Long[] courseIds);
    /**
     * 根据id查询具体信息
     */
    public List<CourseApply> selectByApplyIds(Long[] courseApplyIds);

    /**
     * 查询选课人数
     */
    public int  selectNumberByCourse(Long courseId);
    /**
     *
     *  查询是否选过课
     *  int 返回记录数
     */
    public int selectByUserIdAndCourseId(@Param("userStudentid") String userStudentid,@Param("courseId") Long courseId);
}
