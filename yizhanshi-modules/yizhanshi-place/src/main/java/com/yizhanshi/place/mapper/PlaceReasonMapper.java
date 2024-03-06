package com.yizhanshi.place.mapper;

import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceReason;
import com.yizhanshi.place.domain.Time;

import java.util.List;

public interface PlaceReasonMapper {
    public List<PlaceReason> selectPlaceReasonList(PlaceReason placeReason);
    public PlaceReason selectReasonById(Long reasonId);
    /**
     * 新增原因
     */
    public int insertPlaceReason(PlaceReason placeReason);
    /**
     * 修改原因
     */
    public int updatePlaceReason(PlaceReason placeReason);
    /**
     * 删除原因
     */
    public int deletePlaceReasons(Long[] reasonIds );
}
