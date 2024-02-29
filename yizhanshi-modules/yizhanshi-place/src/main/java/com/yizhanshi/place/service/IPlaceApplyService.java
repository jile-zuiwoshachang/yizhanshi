package com.yizhanshi.place.service;

import com.yizhanshi.place.controller.PlaceApplyController;
import com.yizhanshi.place.domain.Place;
import com.yizhanshi.place.domain.PlaceApply;

import java.util.Date;
import java.util.List;

/**
 * 场地申请 业务层
 *
 * @author hejiale
 */
public interface IPlaceApplyService {
    /**
     * 根据场地id查询场地列表
     * @param placeIds
     * @return
     */
    public List<PlaceApply> selectByPlaceIds(Long[] placeIds);
    /**
     * 根据编号查询具体信息
     */
    public PlaceApply selectPlaceApplyById(Long applyId);
    /**
     * 根据申请单id查询场地申请列表
     * @param placeIds
     * @return
     */
    public List<PlaceApply> selectByApplyIds(Long[] placeIds);
    /**
     * 查询选择的日期且是这个场地的场地信息
     * @param placeId
     * @param chooseDay
     * @return
     */
    public  List<PlaceApply> selectAllPlace(Long placeId, String chooseDay);

    /**
     * 用于管理员审核场地申请单
     * 查询场地申请记录
     * @param placeApply
     * @return
     */
    public List<PlaceApply> selectPlaceApplyList(PlaceApply placeApply);

    /**
     * 修改场地申请单
     * @param placeApply
     * @return
     */
    public int updatePlaceApply(PlaceApply placeApply);

    /**
     * 删除场地申请
     * @param applyIds
     * @return
     */
    public int deletePlaceApply(Long[] applyIds);

    /**
     * 新增场地申请，即场地预约
     * @param placeApply
     * @return
     */
    public int insertPlaceApply(PlaceApply placeApply);
}
