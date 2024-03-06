package com.yizhanshi.course.api;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.constant.ServiceNameConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.factory.RemoteCourseFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 课程服务
 * 场地模块调用课程冲突
 * @author hejiale
 */
@FeignClient(contextId = "remoteCourseService", value = ServiceNameConstants.COURSE_SERVICE, fallbackFactory = RemoteCourseFallbackFactory.class)
public interface RemoteCourseService {
    /**
     * 获取课程冲突信息
     *
     * @param  course  场地id  时间 选择天数
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/courseInfo/timeConflictByCourse")
    public R<Boolean> timeConflictByCourse(@RequestBody Course course, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
