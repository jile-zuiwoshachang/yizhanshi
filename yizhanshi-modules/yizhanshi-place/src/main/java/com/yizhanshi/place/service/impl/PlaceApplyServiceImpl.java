package com.yizhanshi.place.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.course.api.RemoteCourseService;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.api.domain.PlaceApplyTime;
import com.yizhanshi.place.domain.PlaceApplyTimeRelated;
import com.yizhanshi.place.mapper.PlaceApplyMapper;
import com.yizhanshi.place.mapper.PlaceApplyTimeMapper;
import com.yizhanshi.place.mapper.PlaceApplyTimeRelatedMapper;
import com.yizhanshi.place.service.IPlaceApplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 场地预约  业务层
 *
 * @author  hejaile
 */
@Service
public class PlaceApplyServiceImpl implements IPlaceApplyService {
    private static final Logger log = LoggerFactory.getLogger(PlaceServiceImpl.class);

    @Autowired
    private PlaceApplyMapper placeApplyMapper;
    @Autowired
    private  RemoteCourseService  remoteCourseService;
    @Autowired
    private PlaceApplyTimeMapper placeApplyTimeMapper;
    @Autowired
    private PlaceApplyTimeRelatedMapper placeApplyTimeRelatedMapper;


    @Override
    public  PlaceApply selectPlaceApplyById(Long applyId){
        return placeApplyMapper.selectPlaceApplyById(applyId);
    }


    @Override
    public List<PlaceApply> selectPlaceApplyList(PlaceApply placeApply){
        return  placeApplyMapper.selectPlaceApplyList(placeApply);
    }

    /**
     * 当检测到某次更新没有影响任何行时，抛出一个RuntimeException。
     * 这会导致Spring事务管理器回滚所有在当前事务中进行的更改，包括之前可能成功的更新操作。
     * 这种方式确保了数据的一致性，即所有更新要么全部成功，要么在遇到第一个失败时全部回滚。
     * @param placeApply
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePlaceApplyList(List<PlaceApply> placeApply){
        for(PlaceApply item:placeApply){
            placeApplyMapper.updatePlaceApply(item);
        }
    }
    @Override
    public int updatePlaceApply(PlaceApply placeApply){
        return placeApplyMapper.updatePlaceApply(placeApply);
    }

    /**
     * 方法成功执行并且影响了一行或多行数据，正常提交
     * 方法执行但没有影响任何行（比如，传入的applyIds中的ID不存在），方法同样会正常结束，返回0，正常提交
     * SQL错误、数据库连接问题，异常会被抛出，根据@Transactional(rollbackFor = Exception.class)的定义，这时事务会被回滚。
     * @param applyIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePlaceApply(Long[] applyIds){
        placeApplyMapper.deletePlaceApply(applyIds);
        for(Long applyId:applyIds){
            Long[] applyTimeIds=placeApplyTimeRelatedMapper.selectApplyTimeIdsByApplyId(applyId).stream().toArray(Long[]::new);
            placeApplyTimeRelatedMapper.deletePATRelatedByApplyId(applyId);
            placeApplyTimeMapper.deletePlaceApplyTime(applyTimeIds);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertPlaceApply(PlaceApply placeApply){
         placeApplyMapper.insertPlaceApply(placeApply);
        //不冲突则添加关联
        for(PlaceApplyTime placeApplyTime:placeApply.getPlaceApplyTimes()){
            placeApplyTimeMapper.insertPlaceApplyTime(placeApplyTime);
            Long applyTimeId=placeApplyTime.getApplyTimeId();
            Long applyId= placeApply.getApplyId();
            PlaceApplyTimeRelated patRelated = new PlaceApplyTimeRelated();
            patRelated.setApplyId(applyId);
            patRelated.setApplyTimeId(applyTimeId);
            // 插入关联记录
            placeApplyTimeRelatedMapper.insertPATRelated(patRelated);
        }
    }
    /**
     * false不冲突
     * true 冲突
     * 判断时间冲突
     */
    public Boolean timeConflict(List<PlaceApplyTime> dataBase, PlaceApplyTime newApply){
        Boolean flag=Boolean.FALSE;
        //此时天数和场地已经确定
        for(int i=0;i<dataBase.size();i++){
                PlaceApplyTime oldApply= dataBase.get(i);
                Long startOld = oldApply.getTimeStartId();
                Long endOld = oldApply.getTimeEndId();
                Long startNew = newApply.getTimeStartId();
                Long endNew = newApply.getTimeEndId();
                //比较id大小
                if (startOld >= endNew) {
                    flag = Boolean.FALSE;
                } else if (endOld <= startNew) {
                    flag = Boolean.FALSE;
                } else {
                    //冲突直接退出
                    return Boolean.TRUE;
                }
            }
        return  flag;
    }
    @Override
    public Boolean timeConflictByCourse(Course course){
        R<Boolean> booleanR= remoteCourseService.timeConflictByCourse(course,SecurityConstants.INNER);
        if(R.FAIL == booleanR.getCode()){
            throw new ServiceException(booleanR.getMsg());
        }
        return  booleanR.getData();
    }
}
