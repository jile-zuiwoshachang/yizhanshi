package com.yizhanshi.place.service;

import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import com.yizhanshi.place.controller.PlaceApplyController;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceApply;

import java.util.Date;
import java.util.List;

/**
 * 场地预约 业务层
 *
 * @author hejiale
 */
public interface IPlaceApplyService {

    /**
     * 根据编号查询具体信息
     */
    public PlaceApply selectPlaceApplyById(Long applyId);

    /**
     * 用于管理员审核场地预约单
     * 查询场地预约记录
     * @param placeApply
     * @return
     */
    public List<PlaceApply> selectPlaceApplyList(PlaceApply placeApply);

    /**
     * 多选修改场地预约单
     * @param placeApply
     * @return
     */
    public void updatePlaceApplyList(List<PlaceApply> placeApply);
    /**
     * 修改场地预约单
     * @param placeApply
     * @return
     */
    public int updatePlaceApply(PlaceApply placeApply);
    /**
     * 删除场地预约
     * @param applyIds
     * @return
     */
    public void deletePlaceApply(Long[] applyIds);

    /**
     * 新增场地预约，即场地预约
     * @param placeApply
     * @return
     */
    public void insertPlaceApply(PlaceApply placeApply);
    /**
     * 判断时间冲突
     */
    public Boolean timeConflict(List<PlaceApplyTime> dataBase,PlaceApplyTime newApply);
    /**
     * 远程调用课程冲突
     */
    public Boolean timeConflictByCourse(Course course);
}
