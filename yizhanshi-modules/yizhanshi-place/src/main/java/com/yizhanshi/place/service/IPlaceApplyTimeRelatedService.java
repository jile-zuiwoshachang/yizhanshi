package com.yizhanshi.place.service;

import com.yizhanshi.place.domain.PlaceApplyTimeRelated;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPlaceApplyTimeRelatedService {
    /**
     * 根据申请单id获得所有预约时间id
     */
    public List<Long> selectApplyTimeIdsByApplyId(Long applyId);
    /**
     * 批量新增预约和时间关联信息
     *
     * @param placeApplyTimeRelated 预约和时间关联列表
     * @return 结果
     */
    public int insertPATRelated(PlaceApplyTimeRelated placeApplyTimeRelated);


    /**
     * 通过预约单id删除关联
     *
     * @param applyId 需要删除的预约单id
     * @return 结果
     */
    public int deletePATRelatedByApplyId(Long applyId);
    /**
     * 删除预约与时间关联
     * 删除单个
     * @param placeApplyTimeRelated 预约与时间关联信息
     * @return 结果
     */
    public int deletePATRelated(PlaceApplyTimeRelated placeApplyTimeRelated);

    /**
     * 批量删除预约和时间关联信息
     *
     * @param applyId 申请单id
     * @param applyTimeIds 预约时间ids
     * @return 结果
     */
    public int deletePATRelateds(Long applyId,  Long[] applyTimeIds);

}
