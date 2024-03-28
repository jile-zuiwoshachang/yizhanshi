package com.yizhanshi.lost.service;


import com.yizhanshi.lost.domain.Lost;

import java.util.List;

public interface ILostService {
    /**
     * 根据条件分页查询失物列表
     *
     * @param lost 失物信息
     * @return 场失物信息集合信息
     */
    public List<Lost> selectLostList(Lost lost);
    /**
     * 根据失物id查询
     *
     * @param lostId 失物id
     * @return 失物信息信息
     */
    public Lost selectLostById(Long  lostId);
    /**
     * 新增失物信息
     * @param lost
     * @return
     */
    public int insertLost(Lost lost);
    /**
     * 修改失物信息
     * @param lost
     * @return
     */
    public int updateLost(Lost lost);

    /**
     * 删除失物信息
     * @param lostIds
     * @return
     */
    public int deleteLost(Long[] lostIds);
}
