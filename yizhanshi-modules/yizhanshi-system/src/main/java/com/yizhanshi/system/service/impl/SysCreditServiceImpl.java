package com.yizhanshi.system.service.impl;

import com.yizhanshi.common.datascope.annotation.DataScope;
import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.system.mapper.SysCreditMapper;
import com.yizhanshi.system.service.ISysCreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;

/**
 * 信誉 业务层处理
 *
 * @author hejiale
 */
@Service
public class SysCreditServiceImpl implements ISysCreditService {
    private static final Logger log = LoggerFactory.getLogger(SysCreditServiceImpl.class);
    @Autowired
    private SysCreditMapper creditMapper;
    @Autowired
    protected Validator validator;
    /**
     * 根据条件分页查询信誉列表
     *
     * @param credit 用信誉信息
     * @return 信誉信息集合信息
     */
    @Override
    public List<SysCredit> selectCreditList(SysCredit credit)
    {
        return creditMapper.selectCreditList(credit);
    }
    /**
     * 批量删除信誉信息
     *
     * @param creditIds 需要删除的信誉ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCreditByIds(Long[] creditIds){
        return creditMapper.deleteCreditByIds(creditIds);
    }

}
