package com.yizhanshi.course.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.course.api.domain.Course;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 返回给前端用于课程预约的课程列表
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

    public AllCourse(Course course, int chooseNumber, Boolean flag) {
        this.course = course;
        this.chooseNumber = chooseNumber;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("course", course)
                .append("chooseNumber", chooseNumber)
                .append("flag", flag)
                .toString();
    }
}
