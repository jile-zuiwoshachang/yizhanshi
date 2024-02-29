package com.yizhanshi.place.service;

import com.yizhanshi.place.domain.PlaceReason;

import java.util.List;

public interface IPlaceReasonService {

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
