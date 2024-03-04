package com.yizhanshi.place.mapper;

import com.yizhanshi.place.domain.Time;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimeMapper {
    /**
     * 查询时间
     * @param time
     * @return
     */
    public List<Time> selectTimeList(Time time);
    public List<Time> selectTimeByType(String timeType);
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
    public int updateStatus(@Param("status") String status,@Param("timeType") String  timeType);

}
