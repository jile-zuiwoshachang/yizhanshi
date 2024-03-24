package com.yizhanshi.talent.service;

import com.yizhanshi.talent.domain.TalentApply;

import java.util.List;

public interface ITalentApplyService {

    /**
     * 查询该人才的被预约人数
     */
    public int selectNumberByApply(String talentStudentid);

    /**
     * 查询人才预约单列表
     * @param talentApply
     * @return
     */
    public List<TalentApply> selectTalentApplyList(TalentApply talentApply);

    /**
     * 根据编号查询具体信息
     */
    public TalentApply selectTalentApplyById(Long applyId);
    /**
     * 修改人才预约信息
     */
    public  int updateTalentApply(TalentApply talentApply);
    /**
     * 多选修改人才预约信息
     */
    public void updateTalentApplyList(List<TalentApply> talentApplyList);
    /**
     * 删除人才预约信息
     */
    public void deleteTalentApply(Long[] applyIds);
    /**
     * 新增人才预约信息
     */
    public int insertTalentApply(TalentApply talentApply);
}
