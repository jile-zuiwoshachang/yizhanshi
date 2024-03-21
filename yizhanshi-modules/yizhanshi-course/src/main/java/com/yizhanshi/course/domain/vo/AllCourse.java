package com.yizhanshi.course.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.domain.CourseTime;
import com.yizhanshi.place.api.domain.Place;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 返回给前端用于课程预约的课程列表
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AllCourse {
    private Place place;
    /**
     * 所在场地的这天的课程时间信息（包括课程信息和时间）
     */
    private List<CourseTime> courseTimes;


    public AllCourse(Place  place, List<CourseTime> courseTimes) {
        this.place = place;
        this.courseTimes = courseTimes;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public List<CourseTime> getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(List<CourseTime> courseTimes) {
        this.courseTimes = courseTimes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("place", place)
                .append("courseTimes", courseTimes)
                .toString();
    }
}
