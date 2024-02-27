package com.yizhanshi.place.service;

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

}
