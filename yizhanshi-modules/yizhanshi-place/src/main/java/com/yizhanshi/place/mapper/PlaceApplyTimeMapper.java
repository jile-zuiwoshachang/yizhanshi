package com.yizhanshi.place.mapper;

import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import org.apache.ibatis.annotations.Param;


import java.util.List;
/**
 * 场地预约时间表 数据层
 *
 * @author  hejiale
 */
public interface PlaceApplyTimeMapper {
    /**
     * 根据条件分页查询场地预约时间列表
     *
     * @param placeApplyTime 场地预约时间信息
     * @return 场地预约信息集合信息
     */
    public List<PlaceApplyTime> selectPlaceApplyTimeList(PlaceApplyTime placeApplyTime);
    /**
     * 根据编号查询具体信息
     */
    public PlaceApplyTime  selectPlaceApplyTimeById(Long applyTimeId);
    /**
     * 根据编号列表返回List集合
     */
    public List<PlaceApplyTime>  selectPlaceApplyTimeByIds(Long[] applyTimeIds);
    /**
     * 修改场地预约时间信息
     * @param placeApplyTime
     * @return
     */
    public int updatePlaceApplyTime(PlaceApplyTime placeApplyTime);
    /**
     * 删除场地预约信息
     * @param applyTimeIds
     * @return
     */
    public int deletePlaceApplyTime(Long[] applyTimeIds);
    /**
     * 新增场地预约息
     * @param placeApplyTime
     * @return
     */
    public int insertPlaceApplyTime(PlaceApplyTime placeApplyTime);

    /**
     * 根据查询条件，获得当天的场地预约记录
     */
    public List<PlaceApplyTime> selectAllPlace(@Param("placeId") Long placeId, @Param("chooseDay") String chooseDay);
}
