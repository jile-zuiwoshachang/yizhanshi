package com.yizhanshi.talent.service.impl;


import com.yizhanshi.talent.domain.TalentComment;
import com.yizhanshi.talent.mapper.TalentCommentMapper;
import com.yizhanshi.talent.service.ITalentCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TalentCommentServiceImpl implements ITalentCommentService {
    @Autowired
    private TalentCommentMapper talentCommentMapper;

    /**
     * 查询人才评价信息
     *
     * @param commentId 人才评价ID
     * @return 人才评价信息
     */
    @Override
    public TalentComment selectTalentCommentById(Long commentId)
    {
        return talentCommentMapper.selectTalentCommentById(commentId);
    }

    /**
     * 查询人才评价列表
     *
     * @param talentComment 人才评价信息
     * @return 人才评价集合
     */
    @Override
    public List<TalentComment> selectTalentCommentList(TalentComment talentComment)
    {
        return talentCommentMapper.selectTalentCommentList(talentComment);
    }

    /**
     * 新增人才评价
     *
     * @param talentComment 人才评价信息
     * @return 结果
     */
    @Override
    public int insertTalentComment(TalentComment talentComment)
    {
        return talentCommentMapper.insertTalentComment(talentComment);
    }

    /**
     * 修改人才评价
     *
     * @param talentComment 人才评价信息
     * @return 结果
     */
    @Override
    public int updateTalentComment(TalentComment talentComment)
    {
        return talentCommentMapper.updateTalentComment(talentComment);
    }

    /**
     * 批量删除人才评价信息
     *
     * @param commentIds 需要删除的人才评价ID
     * @return 结果
     */
    @Override
    public int deleteTalentCommentByIds(Long[] commentIds)
    {
        return talentCommentMapper.deleteTalentCommentByIds(commentIds);
    }
}
