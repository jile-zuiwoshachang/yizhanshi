package com.yizhanshi.place.service;

import com.yizhanshi.place.api.domain.PlaceApplyTime;

import java.util.List;

public interface IPlaceApplyTimeService {
    /**
     *
     * 查询场地预约时间记录
     * @param placeApplyTime
     * @return
     */
    public List<PlaceApplyTime> selectPlaceApplyTimeList(PlaceApplyTime placeApplyTime);
    /**
     * 根据预约单编号查询信息
     */
    public List<PlaceApplyTime>  selectPlaceApplyTimeByApplyId(Long applyId);
    /**
     * 根据编号查询具体信息
     */
    public PlaceApplyTime selectPlaceApplyTimeById(Long applyTimeId);
    /**
     * 根据编号查询具体信息
     */
    public List<PlaceApplyTime> selectPlaceApplyTimeByIds(Long[] applyTimeIds);
    /**
     * 查询选择的日期且是这个场地的场地信息
     * @param placeId
     * @param chooseDay
     * @return
     */
    public  List<PlaceApplyTime> selectAllPlace(Long placeId, String chooseDay);

    /**
     * 修改场地预约单
     * @param placeApplyTime
     * @return
     */
    public int updatePlaceApplyTime(PlaceApplyTime placeApplyTime);
    /**
     * 删除场地预约时间信息
     * @param applyTimeIds
     * @return
     */
    public void deletePlaceApplyTime(Long[] applyTimeIds);

    /**
     * 新增场地预约时间
     * @param placeApplyTime
     * @return
     */
    public void insertPlaceApplyTime(PlaceApplyTime placeApplyTime);

}
