package com.yizhanshi.place.service;

import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.place.controller.PlaceApplyController;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;

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
    /**
     * 判断时间冲突
     */
    public Boolean timeConflict(List<PlaceApply> dataBase,PlaceApply newApply);
    /**
     * 远程调用课程冲突
     */
    public Boolean timeConflictByCourse(Course course);
}
