package com.yizhanshi.talent.service.impl;

import com.yizhanshi.common.core.constant.SecurityConstants;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.system.api.RemoteUserService;
import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.talent.domain.vo.Talent;
import com.yizhanshi.talent.mapper.TalentLabelMapper;
import com.yizhanshi.talent.service.ITalentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public int insertTalentLabel(Long userStudentid, Long[] labelIds) {
       return  talentLabelMapper.deleteTalentLabels(userStudentid, labelIds);
    }
    @Override
    public List<Talent> selectTalentList(Talent talent){
        return talentLabelMapper.selectTalentList(talent);
    }



}
