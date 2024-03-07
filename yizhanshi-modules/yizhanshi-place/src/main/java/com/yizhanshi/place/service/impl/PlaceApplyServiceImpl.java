package com.yizhanshi.place.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.course.api.RemoteCourseService;
import com.yizhanshi.course.api.domain.Course;
import com.yizhanshi.place.api.domain.PlaceApply;
import com.yizhanshi.place.mapper.PlaceApplyMapper;
import com.yizhanshi.place.service.IPlaceApplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 场地申请  业务层
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

    @Override
    public List<PlaceApply> selectByPlaceIds(Long[] placeIds){
       return  placeApplyMapper.selectByPlaceIds(placeIds);
    }
    @Override
    public  PlaceApply selectPlaceApplyById(Long applyId){
        return placeApplyMapper.selectPlaceApplyById(applyId);
    }
    @Override
    public List<PlaceApply> selectAllPlace(Long placeId, String chooseDay){
        return  placeApplyMapper.selectAllPlace(placeId,chooseDay);
    }

    @Override
    public List<PlaceApply> selectPlaceApplyList(PlaceApply placeApply){
        return  placeApplyMapper.selectPlaceApplyList(placeApply);
    }
    @Override
    public int updatePlaceApply(PlaceApply placeApply){
        return  placeApplyMapper.updatePlaceApply(placeApply);
    }
    @Override
    public int deletePlaceApply(Long[] applyIds){
        return  placeApplyMapper.deletePlaceApply(applyIds);
    }
    @Override
    public int insertPlaceApply(PlaceApply placeApply){
        return  placeApplyMapper.insertPlaceApply(placeApply);
    }
    /**
     * false不冲突
     * true 冲突
     * 判断时间冲突
     */
    public Boolean timeConflict(List<PlaceApply> dataBase, PlaceApply newApply){
        Boolean flag=Boolean.FALSE;
        for(int i=0;i<dataBase.size();i++){
            PlaceApply oldApply= dataBase.get(i);
            //修改信息时，不检查自己的记录
            if(newApply.getApplyId()!=null && Objects.equals(oldApply.getApplyId(), newApply.getApplyId())){
                //跳出此次循环
                break;
            }
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
