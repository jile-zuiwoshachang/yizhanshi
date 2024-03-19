package com.yizhanshi.place.service.impl;

import com.yizhanshi.place.api.domain.PlaceApplyTime;
import com.yizhanshi.place.domain.PlaceApplyTimeRelated;
import com.yizhanshi.place.mapper.PlaceApplyTimeMapper;
import com.yizhanshi.place.mapper.PlaceApplyTimeRelatedMapper;
import com.yizhanshi.place.service.IPlaceApplyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaceApplyTimeServiceImpl implements IPlaceApplyTimeService {
    @Autowired
    private PlaceApplyTimeMapper placeApplyTimeMapper;
    @Autowired
    private PlaceApplyTimeRelatedMapper placeApplyTimeRelatedMapper;
    @Override
    public List<PlaceApplyTime> selectPlaceApplyTimeList(PlaceApplyTime placeApplyTime){
        return placeApplyTimeMapper.selectPlaceApplyTimeList(placeApplyTime);
    }
    /**
     * 根据编号查询具体信息
     */
    @Override
    public PlaceApplyTime selectPlaceApplyTimeById(Long applyTimeId){
        return placeApplyTimeMapper.selectPlaceApplyTimeById(applyTimeId);
    }
    @Override
    public List<PlaceApplyTime> selectPlaceApplyTimeByIds(Long[] applyTimeIds){
        return placeApplyTimeMapper.selectPlaceApplyTimeByIds(applyTimeIds);
    }

    /**
     * 查询选择的日期且是这个场地的场地时间信息
     * @param placeId
     * @param chooseDay
     * @return
     */
    @Override
    public  List<PlaceApplyTime> selectAllPlace(Long placeId, String chooseDay){
        return placeApplyTimeMapper.selectAllPlace(placeId,chooseDay);
    }

    /**
     * 修改场地预约时间单
     * @param placeApplyTime
     * @return
     */
    @Override
    public int updatePlaceApplyTime(PlaceApplyTime placeApplyTime){
        return placeApplyTimeMapper.updatePlaceApplyTime(placeApplyTime);
    }
    /**
     * 删除场地预约时间信息
     * @param applyTimeIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePlaceApplyTime(Long[] applyTimeIds){
        placeApplyTimeMapper.deletePlaceApplyTime(applyTimeIds);
        for(Long applyTimeId:applyTimeIds){
            placeApplyTimeRelatedMapper.deletePATRelatedByApplyTimeId(applyTimeId);
        }
    }

    /**
     * 新增场地预约时间
     * @param placeApplyTime
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertPlaceApplyTime(PlaceApplyTime placeApplyTime){
        Long applyId= placeApplyTime.getApplyId();
        //不冲突则添加关联
        placeApplyTimeMapper.insertPlaceApplyTime(placeApplyTime);
        Long applyTimeId=placeApplyTime.getApplyTimeId();
        PlaceApplyTimeRelated patRelated = new PlaceApplyTimeRelated();
        patRelated.setApplyId(applyId);
        patRelated.setApplyTimeId(applyTimeId);
        // 插入关联记录
        placeApplyTimeRelatedMapper.insertPATRelated(patRelated);
    }
}
