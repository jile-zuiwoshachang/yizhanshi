package com.yizhanshi.place.service.impl;

import com.yizhanshi.place.domain.PlaceApply;
import com.yizhanshi.place.mapper.PlaceApplyMapper;
import com.yizhanshi.place.service.IPlaceApplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Date;
import java.util.List;

/**
 * 场地申请  业务层
 *
 * @author  hejaile
 */
@Service
public class PlaceApplyServiceImpl implements IPlaceApplyService {
    private static final Logger log = LoggerFactory.getLogger(PlaceServiceImpl.class);

    @Autowired
    private PlaceApplyMapper placeApplyMapper;

    @Override
    public List<PlaceApply> selectByPlaceIds(Long[] placeIds){
       return  placeApplyMapper.selectByPlaceIds(placeIds);
    }
    @Override
    public  PlaceApply selectPlaceApplyById(Long applyId){
        return placeApplyMapper.selectPlaceApplyById(applyId);
    }
    @Override
    public List<PlaceApply> selectByApplyIds(Long[] placeApplyIds){
        return  placeApplyMapper.selectByApplyIds(placeApplyIds);
    }
    @Override
    public List<PlaceApply> selectAllPlace(Long placeId, String chooseDay){
        return  placeApplyMapper.selectAllPlace(placeId,chooseDay);
    }

    @Override
    public List<PlaceApply> selectPlaceApplyList(PlaceApply placeApply){
        return  placeApplyMapper.selectPlaceApplyList(placeApply);
    }
    @Override
    public int updatePlaceApply(PlaceApply placeApply){
        return  placeApplyMapper.updatePlaceApply(placeApply);
    }
    @Override
    public int deletePlaceApply(Long[] applyIds){
        return  placeApplyMapper.deletePlaceApply(applyIds);
    }
    @Override
    public int insertPlaceApply(PlaceApply placeApply){
        return  placeApplyMapper.insertPlaceApply(placeApply);
    }
}
