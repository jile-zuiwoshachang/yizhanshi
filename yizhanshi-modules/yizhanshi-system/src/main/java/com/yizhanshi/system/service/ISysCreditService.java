package com.yizhanshi.system.service;

import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.system.api.domain.SysUser;

import java.util.List;

/**
 * 信誉 业务层
 *
 * @author hejiale
 */
public interface ISysCreditService {
    /**
     * 根据条件分页查询信誉列表
     *
     * @param credit 信誉信息
     * @return 信誉信息集合信息
     */
    public List<SysCredit> selectCreditList(SysCredit credit);
    /**
     * 批量删除信誉信息
     *
     * @param creditIds 需要删除的信誉ID
     * @return 结果
     */
    public void deleteCreditByIds(Long[] creditIds);
}
