package com.yizhanshi.lost.mapper;

import com.yizhanshi.lost.domain.LostApply;

import java.util.List;

public interface LostApplyMapper {
    /**
     * 根据条件分页查询失物认领列表
     *
     * @param lostApply 失物认领信息
     * @return 失物认领信息集合信息
     */
    public List<LostApply> selectLostApplyList(LostApply lostApply);
    /**
     * 根据编号查询具体信息
     */
    public LostApply  selectLostApplyById(Long applyId);
    /**
     * 修改失物认领息
     * @param lostApply
     * @return
     */
    public int updateLostApply(LostApply lostApply);
    /**
     * 删除失物认领息
     * @param applyIds
     * @return
     */
    public int deleteLostApply(Long[] applyIds);
    /**
     * 新增失物认领息
     * @param lostApply
     * @return
     */
    public int insertLostApply(LostApply lostApply);
}
