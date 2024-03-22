package com.yizhanshi.talent.service;

import com.yizhanshi.talent.domain.TalentLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITalentLabelService  {
    /**
     * 通过学号查询拥有的标签
     *
     * @param userStudentid 学号
     * @return 结果
     */
    public List<Long> selectLabelIdsByUserStudentid(String userStudentid);

    /**
     * 批量新增用户标签信息
     *
     * @param talentLabelList 用户标签列表
     * @return 结果
     */
    public int insertTalentLabel(TalentLabel talentLabelList);
    /**
     * 通过用户学号删除所有的用户和标签关联
     *
     * @param userStudentid 用户学号
     * @return 结果
     */
    public int deleteTalentLabelByUserStudentid(String userStudentid);

    /**
     * 通过标签删除所有的用户和标签关联
     *
     * @param labelId 需要删除的用户学号
     * @return 结果
     */
    public int deleteTalentLabelByLabelId(Long labelId);
    /**
     * 删除用户和标签关联信息
     * 删除单个
     * @param talentLabel 用户和标签关联信息
     * @return 结果
     */
    public int deleteTalentLabel(TalentLabel talentLabel);

    /**
     * 批量删除用户和标签关联信息
     *
     * @param userStudentid 学号
     * @param labelIds 标签ids
     * @return 结果
     */
    public int deleteTalentLabels( String userStudentid, Long[] labelIds);
}
