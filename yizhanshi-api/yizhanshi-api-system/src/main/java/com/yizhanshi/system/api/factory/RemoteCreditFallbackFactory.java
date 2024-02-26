package com.yizhanshi.system.api.factory;


import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.system.api.RemoteCreditService;
import com.yizhanshi.system.api.domain.SysCredit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.system.api.RemoteUserService;
import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.system.api.model.LoginUser;

/**
 * 信誉服务降级处理
 *
 * @author hejiale
 */
@Component
public class RemoteCreditFallbackFactory implements FallbackFactory<RemoteCreditService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteCreditFallbackFactory.class);

    @Override
    public RemoteCreditService create(Throwable throwable)
    {
        log.error("信誉服务调用失败:{}", throwable.getMessage());
        return new RemoteCreditService()
        {
            @Override
            public R<Boolean> addUserCredit(SysCredit sysCredit, String source)
            {
                return R.fail("修改用户信誉失败:" + throwable.getMessage());
            }

        };
    }
}
