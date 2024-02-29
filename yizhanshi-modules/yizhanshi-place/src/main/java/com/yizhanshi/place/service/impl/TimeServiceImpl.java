package com.yizhanshi.place.service.impl;


import com.yizhanshi.place.domain.PlaceApply;
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
    /**
     * 判断时间冲突
     */
    public Boolean timeConflict(List<PlaceApply> dataBase, PlaceApply newApply){
        Boolean flag=Boolean.FALSE;
        for(int i=0;i<dataBase.size();i++){
            PlaceApply oldApply= dataBase.get(i);
            Long startOld=oldApply.getTimeStartId();
            Long endOld=oldApply.getTimeEndId();
            Long startNew= newApply.getTimeStartId();
            Long endNew=newApply.getTimeEndId();
            //比较id大小
            if (startOld >= endNew) {
               flag=Boolean.FALSE;
            } else if (endOld <= startNew) {
                flag=Boolean.FALSE;
            } else {
                flag=Boolean.TRUE;
            }
        }
        return  flag;
    }

}
