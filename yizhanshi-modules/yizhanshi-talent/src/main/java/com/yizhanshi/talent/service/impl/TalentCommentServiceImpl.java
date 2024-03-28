package com.yizhanshi.talent.service.impl;


import com.yizhanshi.common.core.constant.CacheConstants;
import com.yizhanshi.common.redis.service.RedisService;
import com.yizhanshi.talent.domain.TalentApply;
import com.yizhanshi.talent.domain.TalentComment;
import com.yizhanshi.talent.domain.vo.GoodReview;
import com.yizhanshi.talent.mapper.TalentApplyMapper;
import com.yizhanshi.talent.mapper.TalentCommentMapper;
import com.yizhanshi.talent.service.ITalentCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
@RefreshScope
@Service
public class TalentCommentServiceImpl implements ITalentCommentService {
    @Autowired
    private TalentCommentMapper talentCommentMapper;
    @Autowired
    private TalentApplyMapper talentApplyMapper;
    @Autowired
    private RedisService redisService;
    @Value("${app.goodStars}")
    private String[] stars;


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
    @Transactional(rollbackFor = Exception.class)
    public void insertTalentComment(TalentComment talentComment)
    {
        int row=talentCommentMapper.insertTalentComment(talentComment);
        TalentApply talentApply=new TalentApply();
        talentApply.setApplyId(talentComment.getApplyId());
        talentApply.setCommentId(talentComment.getCommentId());
        talentApplyMapper.updateTalentApply(talentApply);
        if (row > 0) {
            resetConfigCache();
        }
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
        int row= talentCommentMapper.updateTalentComment(talentComment);
        if (row > 0) {
            resetConfigCache();
        }
        return row;
    }

    /**
     * 批量删除人才评价信息
     *
     * @param commentIds 需要删除的人才评价ID
     * @return 结果
     */
    @Override
    public void deleteTalentCommentByIds(Long[] commentIds)
    {
       talentCommentMapper.deleteTalentCommentByIds(commentIds);
       //清空相应申请表字段
       talentApplyMapper.deleteTalentApply(commentIds);
       resetConfigCache();
    }
    /**
     * 以下是好评数设置
     */
    /**
     * 项目启动时，初始化到缓存
     */
    @PostConstruct
    public void init()
    {
        loadingReviewCache();
    }
    /**
     * 加载好评数缓存数据
     * 每个用户都加载
     */
    @Override
    public void loadingReviewCache()
    {
        List<String> userStudentidList = talentCommentMapper.selectAllTalentStudentid();
        for (String userStudentid : userStudentidList)
        {
            //好评数
            int applyNumebrs=talentApplyMapper.selectNumberByApply(userStudentid);
            int goodReviews=talentCommentMapper.selectGoodReviews(userStudentid,stars);
            TalentComment temp=new TalentComment();
            temp.setTalentStudentid(userStudentid);
            int sums=talentCommentMapper.selectTalentCommentList(temp).size();
            // 检查sums是否为0，避免除以0的错误
            int goodPercent = 0;
            if (sums != 0) {
                goodPercent = (int) (((double) goodReviews / sums) * 100); // 计算好评百分比，并转换为整数
            }
            GoodReview goodReview=new GoodReview(applyNumebrs,goodReviews,goodPercent);
            redisService.setCacheObject(getCacheKey(userStudentid), goodReview);
        }
    }
    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey)
    {
        return CacheConstants.BUS_COMMENT_KEY + configKey;
    }

    /**
     * 清空评价类类缓存数据
     */
    @Override
    public void clearReviewCache()
    {
        Collection<String> keys = redisService.keys(CacheConstants.BUS_COMMENT_KEY + "*");
        redisService.deleteObject(keys);
    }

    /**
     * 重置评价数据
     */
    @Override
    public void resetConfigCache()
    {
        clearReviewCache();
        loadingReviewCache();
    }
}
