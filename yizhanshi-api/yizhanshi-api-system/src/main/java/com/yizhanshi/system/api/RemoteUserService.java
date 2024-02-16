package com.yizhanshi.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.constant.ServiceNameConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.system.api.factory.RemoteUserFallbackFactory;
import com.yizhanshi.system.api.model.LoginUser;

/**
 * 用户服务
 * 
 * @author hejiale
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过学号查询用户信息
     *
     * @param userStudentid 学号
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{userStudentid}")
    public R<LoginUser> getUserInfo(@PathVariable("userStudentid") String userStudentid, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
