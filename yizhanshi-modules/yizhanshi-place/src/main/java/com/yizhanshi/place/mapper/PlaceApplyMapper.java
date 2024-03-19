package com.yizhanshi.place.mapper;

import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 场地预约表 数据层
 *
 * @author  hejiale
 */
public  interface PlaceApplyMapper {
    /**
     * 根据条件分页查询场地预约列表
     *
     * @param placeApply 场地预约信息
     * @return 场地预约信息集合信息
     */
    public List<PlaceApply> selectPlaceApplyList(PlaceApply placeApply);
    /**
     * 根据编号查询具体信息
     */
    public PlaceApply  selectPlaceApplyById(Long applyId);
    /**
     * 修改场地预约息
     * @param placeApply
     * @return
     */
    public int updatePlaceApply(PlaceApply placeApply);
    /**
     * 删除场地预约息
     * @param applyIds
     * @return
     */
    public int deletePlaceApply(Long[] applyIds);
    /**
     * 新增场地预约息
     * @param placeApply
     * @return
     */
    public int insertPlaceApply(PlaceApply placeApply);



}
