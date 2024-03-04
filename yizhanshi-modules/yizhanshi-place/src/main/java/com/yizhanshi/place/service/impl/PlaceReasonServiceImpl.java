package com.yizhanshi.place.service.impl;

import com.yizhanshi.place.domain.PlaceReason;
import com.yizhanshi.place.mapper.PlaceReasonMapper;
import com.yizhanshi.place.service.IPlaceReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceReasonServiceImpl implements IPlaceReasonService {
    @Autowired
    private PlaceReasonMapper placeReasonMapper;
    @Override
    public List<PlaceReason> selectPlaceReasonList(PlaceReason placeReason){
        return  placeReasonMapper.selectPlaceReasonList(placeReason);
    }
    @Override
    public PlaceReason selectReasonById(Long reasonId ){
        return  placeReasonMapper.selectReasonById(reasonId);
    }
    /**
     * 新增原因
     */
    @Override
    public int insertPlaceReason(PlaceReason placeReason){
        return placeReasonMapper.insertPlaceReason(placeReason);
    }
    /**
     * 修改原因
     */
    public int updatePlaceReason(PlaceReason placeReason){
        return  placeReasonMapper.updatePlaceReason(placeReason);
    }
    /**
     * 删除原因
     */
    public int deletePlaceReasons(Long[] reasonIds ){
        return placeReasonMapper.deletePlaceReasons(reasonIds);
    }
}
