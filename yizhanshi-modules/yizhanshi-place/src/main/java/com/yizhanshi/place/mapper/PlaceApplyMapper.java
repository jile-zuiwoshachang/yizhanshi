package com.yizhanshi.place.mapper;

import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 场地申请表 数据层
 *
 * @author  hejiale
 */
public  interface PlaceApplyMapper {
    /**
     * 根据条件分页查询场地申请列表
     *
     * @param placeApply 场地申请信息
     * @return 场地申请信息集合信息
     */
    public List<PlaceApply> selectPlaceApplyList(PlaceApply placeApply);
    /**
     * 根据编号查询具体信息
     */
    public PlaceApply  selectPlaceApplyById(Long applyId);
    /**
     * 修改场地申请息
     * @param placeApply
     * @return
     */
    public int updatePlaceApply(PlaceApply placeApply);
    /**
     * 删除场地申请息
     * @param applyIds
     * @return
     */
    public int deletePlaceApply(Long[] applyIds);
    /**
     * 新增场地申请息
     * @param placeApply
     * @return
     */
    public int insertPlaceApply(PlaceApply placeApply);
    /**
     * 查看该场地有多少个被预约记录
     */
    public List<PlaceApply> selectByPlaceIds(Long[] placeIds);

    /**
     * 根据查询条件，获得当天的场地申请记录
     */
    public List<PlaceApply> selectAllPlace(@Param("placeId") Long placeId,@Param("chooseDay") String chooseDay);


}
