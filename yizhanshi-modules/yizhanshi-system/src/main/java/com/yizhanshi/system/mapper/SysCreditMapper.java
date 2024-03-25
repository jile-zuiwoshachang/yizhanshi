package com.yizhanshi.system.mapper;

import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.system.api.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表 数据层
 * 
 * @author hejiale
 */
public interface SysCreditMapper
{
    /**
     * 根据条件分页查询用户列表
     * 
     * @param credit 信誉信息
     * @return 信誉信息集合信息
     */
    public List<SysCredit> selectCreditList(SysCredit credit);


    /**
     * 根据id获取详细信息
     */
    public SysCredit  selectCreditById(Long creditId);
    /**
     * 新增信誉信息
     * 
     * @param credit 信誉信息
     * @return 结果
     */
    public int insertCredit(SysCredit credit);

    /**
     * 批量删除信誉信息
     * 
     * @param creditIds 需要删除的信誉ID
     * @return 结果
     */
    public int deleteCreditByIds(Long[] creditIds);

}
