package com.yizhanshi.place.api.factory;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.place.api.RemotePlaceService;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
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
            public R<Boolean> timeConflictByPlace(List<PlaceApplyTime> placeApplyTimes , String source)
            {
                return R.fail("调用场地服务失败:" + throwable.getMessage());
            }
            @Override
            public R<List<Place>> selectList(Place place , String source){
                return R.fail("调用场地服务失败:" + throwable.getMessage());
            }

        };
    }

}
