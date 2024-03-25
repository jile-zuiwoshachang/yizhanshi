package com.yizhanshi.talent.service;



import com.yizhanshi.talent.domain.TalentComment;

import java.util.List;
/**
 * 人才评价 服务层
 *
 * @author hejiale
 */
public interface ITalentCommentService {
    /**
     * 查询人才评价信息
     *
     * @param commentId 人才评价ID
     * @return 人才评价信息
     */
    public TalentComment selectTalentCommentById(Long commentId);

    /**
     * 查询人才评价列表
     *
     * @param TalentComment 人才评价信息
     * @return 人才评价集合
     */
    public List<TalentComment> selectTalentCommentList(TalentComment TalentComment);

    /**
     * 新增人才评价
     *
     * @param TalentComment 人才评价信息
     * @return 结果
     */
    public void insertTalentComment(TalentComment TalentComment);

    /**
     * 修改人才评价
     *
     * @param TalentComment 人才评价信息
     * @return 结果
     */
    public int updateTalentComment(TalentComment TalentComment);

    /**
     * 批量删除人才评价信息
     *
     * @param commentIds 需要删除的人才评价ID
     * @return 结果
     */
    public void deleteTalentCommentByIds(Long[] commentIds);
    /**
     * 以下是初始化到缓存
     */
    /**
     * 加载好评缓存数据
     */
    public void loadingReviewCache();
    /**
     * 清空评价类类缓存数据
     */
    public void clearReviewCache();
    /**
     * 重置评价数据
     */
    public void resetConfigCache();
}
