package com.yizhanshi.talent.service.impl;

import com.yizhanshi.talent.domain.TalentApply;
import com.yizhanshi.talent.mapper.TalentApplyMapper;
import com.yizhanshi.talent.service.ITalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentApplyServiceImpl implements ITalentService {
    @Autowired
    private TalentApplyMapper talentApplyMapper;

    @Override
    public  int selectNumberByApply(String talentStudentid){
        return  talentApplyMapper.selectNumberByApply(talentStudentid);
    }

    @Override
    public List<TalentApply> selectTalentApplyList(TalentApply talentApply){
        return  talentApplyMapper.selectTalentApplyList(talentApply);
    }
    @Override
    public  TalentApply selectTalentApplyById(Long applyId){
        return  talentApplyMapper.selectTalentApplyById(applyId);
    }
    @Override
    public   int updateTalentApply(TalentApply talentApply){
        return  talentApplyMapper.updateTalentApply(talentApply);
    }
    @Override
    public  int deleteCourseApply(Long[] applyIds){
        return  talentApplyMapper.deleteTalentApply(applyIds);
    }
    @Override
    public int insertCourseApply(TalentApply talentApply){
        return talentApplyMapper.insertTalentApply(talentApply);
    }
}
