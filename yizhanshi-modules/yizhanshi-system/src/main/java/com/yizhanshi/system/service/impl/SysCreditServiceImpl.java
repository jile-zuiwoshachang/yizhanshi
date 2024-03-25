package com.yizhanshi.system.service.impl;

import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.exception.ServiceException;
import com.yizhanshi.common.datascope.annotation.DataScope;
import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.system.mapper.SysCreditMapper;
import com.yizhanshi.system.mapper.SysUserMapper;
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
    private SysUserMapper sysUserMapper;

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
    public void deleteCreditByIds(Long[] creditIds){
        for(Long creditId:creditIds){
            int score=creditMapper.selectCreditById(creditId).getCreditNumber();
            String userStudentid=creditMapper.selectCreditById(creditId).getUserStudentid();
            SysUser sysUser=sysUserMapper.selectUserByUserStudentid(userStudentid);
            int newScore=sysUser.getUserScore()-score;
            if(newScore>100 || newScore<0){
                throw  new ServiceException("该用户信誉已经超过或低于限定范围，不允许删除");
            }
            sysUserMapper.updateUserScore(newScore,userStudentid);
        }
        creditMapper.deleteCreditByIds(creditIds);
    }

}
