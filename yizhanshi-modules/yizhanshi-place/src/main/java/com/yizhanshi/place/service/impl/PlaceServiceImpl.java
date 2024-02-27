package com.yizhanshi.place.service.impl;

import com.yizhanshi.common.datascope.annotation.DataScope;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.place.domain.Place;
import com.yizhanshi.place.mapper.PlaceMapper;
import com.yizhanshi.place.service.IPlaceService;
import com.yizhanshi.system.api.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;

/**
 * 场地 业务层
 *
 * @author hejiale
 */
@Service
public class PlaceServiceImpl  implements IPlaceService {
    private static final Logger log = LoggerFactory.getLogger(PlaceServiceImpl.class);

    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    protected Validator validator;


    @Override
    public List<Place> selectPlaceList(Place place)
    {
        return placeMapper.selectPlaceList(place);
    }
    @Override
    public Place selectPlaceById(Long placeId)
    {
        return placeMapper.selectPlaceById(placeId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPlace(Place place){
        String userName= SecurityUtils.getUsername();
        place.setCreateBy(userName);
        int rows = placeMapper.insertPlace(place);
        return rows;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePlace(Place place){
        int rows = placeMapper.updatePlace(place);
        return rows;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int  deletePlace(Long[] placeIds){
        int rows = placeMapper.deletePlace(placeIds);
        return rows;
    }


}
