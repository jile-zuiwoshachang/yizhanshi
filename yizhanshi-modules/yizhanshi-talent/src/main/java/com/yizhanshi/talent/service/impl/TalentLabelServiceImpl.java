package com.yizhanshi.talent.service.impl;

import com.yizhanshi.talent.domain.TalentLabel;
import com.yizhanshi.talent.mapper.TalentLabelMapper;
import com.yizhanshi.talent.service.ITalentLabelService;
import com.yizhanshi.talent.service.ITalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TalentLabelServiceImpl implements ITalentLabelService {
    @Autowired
    private TalentLabelMapper talentLabelMapper;
    /**
     * 通过学号查询拥有的标签
     *
     * @param userStudentid 学号
     * @return 结果
     */
    @Override
    public List<Long> selectLabelIdsByUserStudentid(String userStudentid){
        return talentLabelMapper.selectLabelIdsByUserStudentid(userStudentid);
    }

    /**
     * 批量新增用户标签信息
     *
     * @param talentLabel 用户标签关联
     * @return 结果
     */
    @Override
    public int insertTalentLabel(TalentLabel talentLabel){
        return talentLabelMapper.insertTalentLabel(talentLabel);
    }
    /**
     * 通过用户学号删除所有的用户和标签关联
     *
     * @param userStudentid 用户学号
     * @return 结果
     */
    @Override
    public int deleteTalentLabelByUserStudentid(String userStudentid){
        return talentLabelMapper.deleteTalentLabelByUserStudentid(userStudentid);
    }

    /**
     * 通过标签删除所有的用户和标签关联
     *
     * @param labelId 需要删除的用户学号
     * @return 结果
     */
    @Override
    public int deleteTalentLabelByLabelId(Long labelId){
        return talentLabelMapper.deleteTalentLabelByLabelId(labelId);
    }
    /**
     * 删除用户和标签关联信息
     * 删除单个
     * @param talentLabel 用户和标签关联信息
     * @return 结果
     */
    @Override
    public int deleteTalentLabel(TalentLabel talentLabel){
        return talentLabelMapper.deleteTalentLabel(talentLabel);
    }

    /**
     * 批量删除用户和标签关联信息
     *
     * @param userStudentid 学号
     * @param labelIds 标签ids
     * @return 结果
     */
    @Override
    public int deleteTalentLabels( String userStudentid, Long[] labelIds){
        return talentLabelMapper.deleteTalentLabels(userStudentid,labelIds);
    }
}
