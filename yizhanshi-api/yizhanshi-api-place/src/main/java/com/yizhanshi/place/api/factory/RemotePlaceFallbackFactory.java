package com.yizhanshi.place.api.factory;

import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.place.api.RemotePlaceService;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class RemotePlaceFallbackFactory implements FallbackFactory<RemotePlaceService> {
    private static final Logger log = LoggerFactory.getLogger(RemotePlaceFallbackFactory.class);

    @Override
    public RemotePlaceService create(Throwable throwable)
    {
        log.error("场地服务调用失败:{}", throwable.getMessage());
        return new RemotePlaceService()
        {
            @Override
            public R<Boolean> timeConflictByPlace(PlaceApply placeApply , String source)
            {
                return R.fail("调用场地服务失败:" + throwable.getMessage());
            }

        };
    }
}
