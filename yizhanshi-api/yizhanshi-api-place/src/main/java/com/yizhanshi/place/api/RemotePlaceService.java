package com.yizhanshi.place.api;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.constant.ServiceNameConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.factory.RemotePlaceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 场地服务
 * 场地模块调用课程冲突
 * @author hejiale
 */
@FeignClient(contextId = "remotePlaceService", value = ServiceNameConstants.PLACE_SERVICE, fallbackFactory = RemotePlaceFallbackFactory.class)
public interface RemotePlaceService {
    /**
     * 获取场地冲突信息
     *
     * @param   placeApply 场地id  时间   选择天数
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/placeApply/timeConflictByPlace")
    public R<Boolean> timeConflictByPlace(@RequestBody PlaceApply placeApply,  @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
