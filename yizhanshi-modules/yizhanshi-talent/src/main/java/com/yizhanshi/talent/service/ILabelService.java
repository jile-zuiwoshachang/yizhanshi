package com.yizhanshi.talent.service;

import com.yizhanshi.talent.domain.Label;

import java.util.List;

public interface ILabelService {
    /**
     * 根据条件分页查询标签列表
     *
     * @param label 标签信息
     * @return 标签信息集合信息
     */
    public List<Label> selectLabelList(Label label);
    /**
     * 根据标签id查询
     *
     * @param labelId 标签id
     * @return 标签信息信息
     */
    public Label selectLabelById(Long  labelId);
    /**
     * 新增标签信息
     * @param label
     * @return
     */
    public int insertLabel(Label label);
    /**
     * 修改标签信息
     * @param label
     * @return
     */
    public int updateLabel(Label label);

    /**
     * 删除标签信息
     * @param labelIds
     * @return
     */
    public int deleteLabel(Long[] labelIds);
    /**
     * 获取所有标签类型
     */
    public List<String> selectAllType( );
}
