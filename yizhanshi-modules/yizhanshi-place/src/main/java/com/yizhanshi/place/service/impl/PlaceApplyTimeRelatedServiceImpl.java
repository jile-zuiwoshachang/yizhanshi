package com.yizhanshi.place.service.impl;


import com.yizhanshi.place.domain.PlaceApplyTimeRelated;
import com.yizhanshi.place.mapper.PlaceApplyTimeRelatedMapper;
import com.yizhanshi.place.service.IPlaceApplyTimeRelatedService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceApplyTimeRelatedServiceImpl implements IPlaceApplyTimeRelatedService {
    @Autowired
    private PlaceApplyTimeRelatedMapper placeApplyTimeRelatedMapper;
    @Override
    public List<Long> selectApplyTimeIdsByApplyId(Long applyId){
        return placeApplyTimeRelatedMapper.selectApplyTimeIdsByApplyId(applyId);
    }
    /**
     * 批量新增预约和时间关联信息
     *
     * @param placeApplyTimeRelated 预约和时间关联列表
     * @return 结果
     */
    @Override
    public int insertPATRelated(PlaceApplyTimeRelated placeApplyTimeRelated){
        return placeApplyTimeRelatedMapper.insertPATRelated(placeApplyTimeRelated);
    }


    /**
     * 通过预约单id删除关联
     *
     * @param applyId 需要删除的预约单id
     * @return 结果
     */
    public int deletePATRelatedByApplyId(Long applyId){
        return  placeApplyTimeRelatedMapper.deletePATRelatedByApplyId(applyId);
    }

    /**
     * 删除预约与时间关联
     * 删除单个
     * @param placeApplyTimeRelated 预约与时间关联信息
     * @return 结果
     */
    public int deletePATRelated(PlaceApplyTimeRelated placeApplyTimeRelated){
        return  placeApplyTimeRelatedMapper.deletePATRelated(placeApplyTimeRelated);
    }

    /**
     * 批量删除预约和时间关联信息
     *
     * @param applyId 申请单id
     * @param applyTimeIds 预约时间ids
     * @return 结果
     */
    public int deletePATRelateds( Long applyId,  Long[] applyTimeIds){
        return placeApplyTimeRelatedMapper.deletePATRelateds(applyId,applyTimeIds);
    }

}
