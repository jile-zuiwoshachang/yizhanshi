package com.yizhanshi.course.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.course.domain.Course;

/**
 * 返回给前端用于课程申请的课程列表
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AllCourse {
    private Course course;
    /**
     * 已选人数
     */
    private int chooseNumber;
    /**
     * 可选课标志
     * true：可
     * false: 不可
     */
    private  Boolean flag;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getChooseNumber() {
        return chooseNumber;
    }

    public void setChooseNumber(int chooseNumber) {
        this.chooseNumber = chooseNumber;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
