package com.yizhanshi.talent.service;

import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.talent.domain.vo.Talent;

import java.util.List;

public interface ITalentService {
    /**
     * 修改用户，远程调用
     */
   public Boolean updateUser(SysUser sysUser);
    /**
     * 新增用户与标签关联
     */
    public int insertTalentLabel(Long userStudentid,Long[] labelIds);
    /**
     * 人才查询
     */
    public List<Talent> selectTalentList(Talent talent);
    /**
     * 修改人才
     */
    public  int updateTalent(Talent talnet);
}
