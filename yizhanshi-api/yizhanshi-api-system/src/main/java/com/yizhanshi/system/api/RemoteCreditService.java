package com.yizhanshi.system.api;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.constant.ServiceNameConstants;
import com.yizhanshi.common.core.domain.R;

import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.system.api.factory.RemoteCreditFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

/**
 * 信誉服务
 * 方便任何模块调用信誉服务
 * @author hejiale
 */
@FeignClient(contextId = "remoteCreditService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteCreditFallbackFactory.class)
public interface RemoteCreditService {

    /**
     * 新增信誉信息
     *
     * @param sysCredit 信誉信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/credit")
    public R<Boolean> addUserCredit(@RequestBody SysCredit sysCredit, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
