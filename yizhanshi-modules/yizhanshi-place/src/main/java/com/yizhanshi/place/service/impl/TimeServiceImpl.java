package com.yizhanshi.place.service.impl;


import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.domain.Time;
import com.yizhanshi.place.mapper.TimeMapper;
import com.yizhanshi.place.service.ITimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TimeServiceImpl implements ITimeService {
    @Autowired
    private TimeMapper timeMapper;

    /**
     * 查询时间
     */

    @Override
    public List<Time> selectTimeList(@RequestBody Time time){
        return timeMapper.selectTimeList(time);
    }
    @Override
    public List<Time> selectTrueTime(){
        return timeMapper.selectTrueTime();
    }
    @Override
    public Time selectTimeById(Long timeId){
        return timeMapper.selectTimeById(timeId);
    }
    /**
     * 新增时间
     */
    public int insertTime(Time time){
        return timeMapper.insertTime(time);
    }
    /**
     * 修改时间
     */
    public int updateTime(Time time){
        return timeMapper.updateTime(time);
    }


    /**
     * 删除时间
     */
    public int deleteTime(String timeType){
        return timeMapper.deleteTime(timeType);
    }
    /**
     * 修改状态
     */
    public int updateStatus(String status,String timeType){
        return timeMapper.updateStatus(status,timeType);
    }


}
