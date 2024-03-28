package com.yizhanshi.lost.service.impl;

import com.yizhanshi.lost.domain.LostApply;
import com.yizhanshi.lost.mapper.LostApplyMapper;
import com.yizhanshi.lost.service.ILostApplyService;
import com.yizhanshi.lost.service.ILostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LostApplyServiceImpl implements ILostApplyService {
    @Autowired
    private LostApplyMapper lostApplyMapper;
    /**
     * 根据条件分页查询失物认领列表
     *
     * @param lostApply 失物认领信息
     * @return 失物认领信息集合信息
     */
    @Override
    public List<LostApply> selectLostApplyList(LostApply lostApply){
        return lostApplyMapper.selectLostApplyList(lostApply);
    }
    /**
     * 根据编号查询具体信息
     */
    @Override
    public LostApply  selectLostApplyById(Long applyId){
        return lostApplyMapper.selectLostApplyById(applyId);
    }
    /**
     * 修改失物认领信息
     * @param lostApply
     * @return
     */
    @Override
    public int updateLostApply(LostApply lostApply){
        return lostApplyMapper.updateLostApply(lostApply);
    }
    /**
     * 删除失物认领信息
     * @param applyIds
     * @return
     */
    @Override
    public int deleteLostApply(Long[] applyIds){
        return lostApplyMapper.deleteLostApply(applyIds);
    }
    /**
     * 新增失物认领信息
     * @param lostApply
     * @return
     */
    @Override
    public int insertLostApply(LostApply lostApply){
        return lostApplyMapper.insertLostApply(lostApply);
    }
}
