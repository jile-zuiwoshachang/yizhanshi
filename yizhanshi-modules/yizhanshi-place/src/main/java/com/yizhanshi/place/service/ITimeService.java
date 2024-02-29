package com.yizhanshi.place.service;

import com.yizhanshi.place.domain.PlaceApply;
import com.yizhanshi.place.domain.Time;

import java.util.List;


/**
 * 时间服务
 */
public interface ITimeService {
    /**
     * 查询时间
     */
    public List<Time> selectTimeList(Time time);
    public List<Time> selectTrueTime();
    public Time selectTimeById(Long timeId);
    /**
     * 新增时间
     */
    public int insertTime(Time time);
    /**
     * 修改时间
     */
    public int updateTime(Time time);
    /**
     * 删除时间
     */
    public int deleteTime(String timeType);
    /**
     * 修改状态
     */
    public int updateStatus(String status,String timeType);
    /**
     * 判断时间冲突
     */
    public Boolean timeConflict(List<PlaceApply> dataBase,PlaceApply newApply);

}
