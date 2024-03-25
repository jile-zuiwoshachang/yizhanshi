package com.yizhanshi.talent.service.impl;

import com.yizhanshi.common.core.constant.CacheConstants;
import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.common.redis.service.RedisService;
import com.yizhanshi.system.api.RemoteUserService;
import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.talent.domain.Label;
import com.yizhanshi.talent.domain.TalentComment;
import com.yizhanshi.talent.domain.TalentLabel;
import com.yizhanshi.talent.domain.vo.GoodReview;
import com.yizhanshi.talent.domain.vo.Talent;
import com.yizhanshi.talent.mapper.TalentApplyMapper;
import com.yizhanshi.talent.mapper.TalentCommentMapper;
import com.yizhanshi.talent.mapper.TalentLabelMapper;
import com.yizhanshi.talent.service.ITalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class TalentServiceImpl implements ITalentService {
    @Autowired
    TalentLabelMapper talentLabelMapper;
    @Autowired
    RemoteUserService remoteUserService;



    @Override
    public Boolean updateUser(SysUser sysUser) {
        R<Boolean> booleanR = remoteUserService.editByTalent(sysUser, SecurityConstants.INNER);
        if (R.FAIL == booleanR.getCode()) {
            throw new ServiceException(booleanR.getMsg());
        }
        return booleanR.getData();
    }

    @Override
    public int insertTalentLabel(TalentLabel talentLabel) {
        return     talentLabelMapper.insertTalentLabel(talentLabel);
    }
    @Override
    public List<Talent> selectTalentList(Talent talent){
        return talentLabelMapper.selectTalentList(talent);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTalent(Talent talent){
        //删除该用户拥有的所有标签
        String studentid=talent.getSysUser().getUserStudentid();
        talentLabelMapper.deleteTalentLabelByUserStudentid(studentid);
        List<TalentLabel> list=new ArrayList<>();
        List<Label> labels=talent.getLabels();
        for(Label label:labels){
            TalentLabel talentLabel=new TalentLabel();
            talentLabel.setLabelId(label.getLabelId());
            talentLabel.setUserStudentid(studentid);
            list.add(talentLabel);
            talentLabelMapper.insertTalentLabel(talentLabel);
        }
    }

}
