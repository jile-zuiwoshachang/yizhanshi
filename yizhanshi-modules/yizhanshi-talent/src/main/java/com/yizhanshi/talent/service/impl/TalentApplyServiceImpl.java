package com.yizhanshi.talent.service.impl;

import com.yizhanshi.common.core.utils.StringUtils;
import com.yizhanshi.talent.domain.TalentApply;
import com.yizhanshi.talent.domain.constants.ApplyConstants;
import com.yizhanshi.talent.mapper.TalentApplyMapper;
import com.yizhanshi.talent.mapper.TalentCommentMapper;
import com.yizhanshi.talent.service.ITalentApplyService;
import com.yizhanshi.talent.service.ITalentCommentService;
import com.yizhanshi.talent.service.ITalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TalentApplyServiceImpl implements ITalentApplyService {
    @Autowired
    private TalentApplyMapper talentApplyMapper;
    @Autowired
    private TalentCommentMapper talentCommentMapper;
    @Autowired
    private ITalentCommentService commentService;

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
    @Transactional(rollbackFor = Exception.class)
    public   void updateTalentApplyList(List<TalentApply> talentApplyList){
        for(TalentApply item:talentApplyList){
            talentApplyMapper.updateTalentApply(item);
        }
        //同意则重置redis里的接单数缓存
        if(StringUtils.equals(talentApplyList.get(0).getStatus(), ApplyConstants.AGREESTATUS)){
            commentService.resetConfigCache();
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public  void deleteTalentApply(Long[] applyIds){
        talentApplyMapper.deleteTalentApply(applyIds);
        Long[] commentIds=talentApplyMapper.selectAllCommentIdsByApplyIds(applyIds);
        if(commentIds.length>0){
        talentCommentMapper.deleteTalentCommentByIds(commentIds);
        }
    }
    @Override
    public int insertTalentApply(TalentApply talentApply){
        return talentApplyMapper.insertTalentApply(talentApply);
    }
}
