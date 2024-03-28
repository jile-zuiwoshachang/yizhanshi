package com.yizhanshi.lost.service.impl;


import com.yizhanshi.lost.domain.Lost;
import com.yizhanshi.lost.mapper.LostMapper;
import com.yizhanshi.lost.service.ILostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LostServiceImpl implements ILostService {
    @Autowired
    private LostMapper lostMapper;
    /**
     * 根据条件分页查询失物列表
     *
     * @param lost 失物信息
     * @return 场失物信息集合信息
     */
    @Override
    public List<Lost> selectLostList(Lost lost){
        return lostMapper.selectLostList(lost);
    }
    /**
     * 根据失物id查询
     *
     * @param lostId 失物id
     * @return 失物信息信息
     */
    @Override
    public Lost selectLostById(Long  lostId){
        return lostMapper.selectLostById(lostId);
    }
    /**
     * 新增失物信息
     * @param lost
     * @return
     */
    @Override
    public int insertLost(Lost lost){
        return lostMapper.insertLost(lost);
    }
    /**
     * 修改失物信息
     * @param lost
     * @return
     */
    @Override
    public int updateLost(Lost lost){
        return lostMapper.updateLost(lost);
    }

    /**
     * 删除失物信息
     * @param lostIds
     * @return
     */
    @Override
    public int deleteLost(Long[] lostIds){
        return lostMapper.deleteLost(lostIds);
    }
}
