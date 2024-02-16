package com.yizhanshi.auth.service;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yizhanshi.common.core.constant.CacheConstants;
import com.yizhanshi.common.core.constant.Constants;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.common.redis.service.RedisService;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.system.api.domain.SysUser;

/**
 * 登录密码方法
 * 
 * @author hejiale
 */
@Component
public class SysPasswordService
{
    @Autowired
    private RedisService redisService;

    private int maxRetryCount = CacheConstants.PASSWORD_MAX_RETRY_COUNT;

    private Long lockTime = CacheConstants.PASSWORD_LOCK_TIME;

    @Autowired
    private SysRecordLogService recordLogService;

    /**
     * 登录账户密码错误次数缓存键名
     * 
     * @param userStudentid 学号
     * @return 缓存键key
     */
    private String getCacheKey(String userStudentid)
    {
        return CacheConstants.PWD_ERR_CNT_KEY + userStudentid;
    }

    public void validate(SysUser user, String password)
    {
        String userStudentid = user.getUserStudentid();
        String username = user.getUserName();

        Integer retryCount = redisService.getCacheObject(getCacheKey(userStudentid));

        if (retryCount == null)
        {
            retryCount = 0;
        }

        if (retryCount >= Integer.valueOf(maxRetryCount).intValue())
        {
            String errMsg = String.format("密码输入错误%s次，帐户锁定%s分钟", maxRetryCount, lockTime);
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL,errMsg);
            throw new ServiceException(errMsg);
        }
        // 判断输入的密码和真实密码是否相同
        if (!matches(user, password))
        {
            retryCount = retryCount + 1;
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, String.format("密码输入错误%s次", retryCount));
            redisService.setCacheObject(getCacheKey(userStudentid), retryCount, lockTime, TimeUnit.MINUTES);
            throw new ServiceException("用户不存在/密码错误");
        }
        else
        {
            clearLoginRecordCache(userStudentid);
        }
    }

    public boolean matches(SysUser user, String rawPassword)
    {
        return SecurityUtils.matchesPassword(rawPassword, user.getUserPassword());
    }

    public void clearLoginRecordCache(String loginName)
    {
        if (redisService.hasKey(getCacheKey(loginName)))
        {
            redisService.deleteObject(getCacheKey(loginName));
        }
    }
}
