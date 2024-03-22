package com.yizhanshi.talent.service.impl;

import com.yizhanshi.talent.domain.Label;
import com.yizhanshi.talent.mapper.LabelMapper;
import com.yizhanshi.talent.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements ILabelService {
    @Autowired
    private LabelMapper labelMapper;
    /**
     * 根据条件分页查询标签列表
     *
     * @param label 标签信息
     * @return 标签信息集合信息
     */
    @Override
    public List<Label> selectLabelList(Label label){
        return labelMapper.selectLabelList(label);
    }
    /**
     * 根据标签id查询
     *
     * @param labelId 标签id
     * @return 标签信息信息
     */
    @Override
    public Label selectLabelById(Long  labelId) {
        return labelMapper.selectLabelById(labelId);
    }
    @Override
    public List<Label> selectLabelByIds(Long[]  labelIds) {
        return labelMapper.selectLabelByIds(labelIds);
    }
    /**
     * 新增标签信息
     * @param label
     * @return
     */
    @Override
    public int insertLabel(Label label){
        return labelMapper.insertLabel(label);
    }
    /**
     * 修改标签信息
     * @param label
     * @return
     */
    @Override
    public int updateLabel(Label label){
        return labelMapper.updateLabel(label);
    }

    /**
     * 删除标签信息
     * @param labelIds
     * @return
     */
    @Override
    public int deleteLabel(Long[] labelIds){
        return labelMapper.deleteLabel(labelIds);
    }
    /**
     * 获取所有标签类型
     */
    @Override
    public List<String> selectAllType( ){
        return labelMapper.selectAllType();
    }
}
