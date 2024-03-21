package com.yizhanshi.course.api.factory;

import com.yizhanshi.course.api.RemoteCourseService;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.course.api.domain.CourseTime;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import com.yizhanshi.common.core.domain.R;

import java.util.List;
import java.util.Map;

@Component
public class RemoteCourseFallbackFactory  implements FallbackFactory<RemoteCourseService>{
    private static final Logger log = LoggerFactory.getLogger(RemoteCourseFallbackFactory.class);

    @Override
    public RemoteCourseService create(Throwable throwable)
    {
        log.error("课程服务调用失败:{}", throwable.getMessage());
        return new RemoteCourseService()
        {
            @Override
            public R<Boolean> timeConflictByCourse(List<CourseTime> courseTimes , String source)
            {
                return R.fail("调用课程服务失败:" + throwable.getMessage());
            }

        };
    }
}
