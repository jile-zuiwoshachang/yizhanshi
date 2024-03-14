package com.yizhanshi.talent.mapper;

import com.yizhanshi.talent.domain.TalentLabel;
import com.yizhanshi.talent.domain.vo.Talent;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface TalentLabelMapper {

    /**
     * 通过学号查询拥有的标签
     *
     * @param userStudentid 学号
     * @return 结果
     */
    public TalentLabel selectLabelByUserStudentid(Long userStudentid);

    /**
     * 批量新增用户标签信息
     *
     * @param talentLabelList 用户标签列表
     * @return 结果
     */
    public int insertTalentLabel(List<TalentLabel> talentLabelList);
    /**
     * 通过用户学号删除所有的用户和标签关联
     *
     * @param userStudentid 用户学号
     * @return 结果
     */
    public int deleteTalentLabelByUserStudentid(Long userStudentid);

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
    public int deleteTalentLabels(@Param("userStudentid") Long userStudentid, @Param("labelIds") Long[] labelIds);

    /**
     * ************************其他业务*******************************
     */
    /**
     * 人才查询
     */
    public List<Talent> selectTalentList(Talent talent);
    /**
     * 人才修改-用户修改
     */
    public int updateTalentUser(Talent talent);
    /**
     * 人才修改-标签修改
     */
    public int updateTalentLabel(Talent talent);
}
