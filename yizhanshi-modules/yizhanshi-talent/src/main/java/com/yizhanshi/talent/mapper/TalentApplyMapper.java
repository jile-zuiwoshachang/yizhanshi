package com.yizhanshi.talent.mapper;

import com.yizhanshi.talent.domain.TalentApply;

import java.util.List;

public interface TalentApplyMapper {
    /**
     * 根据条件分页查询人才申请列表
     *
     * @param talentApply 人才申请信息
     * @return 人才申请信息集合信息
     */
    public List<TalentApply> selectTalentApplyList(TalentApply talentApply);
    /**
     * 根据编号查询具体信息
     */
    public TalentApply  selectTalentApplyById(Long applyId);
    /**
     * 修改人才申请息
     * @param talentApply
     * @return
     */
    public int updateTalentApply(TalentApply talentApply);
    /**
     * 删除人才申请息
     * @param applyIds
     * @return
     */
    public int deleteTalentApply(Long[] applyIds);
    /**
     * 根据commentIds删除相应记录
     * 其实只是更新，将字段设置为空
     */
    public int deleteCommentIdsByIds(Long[] commentIds);
    /**
     * 新增人才申请息
     * @param talentApply
     * @return
     */
    public int insertTalentApply(TalentApply talentApply);
    /**
     * 查看该人才有多少个被预约记录
     */
    public int selectNumberByApply(String talentStudentid);
    /**
     * 获取applyIds的所有评论记录
     */
    public Long[] selectAllCommentIdsByApplyIds(Long[] applyIds);

}
