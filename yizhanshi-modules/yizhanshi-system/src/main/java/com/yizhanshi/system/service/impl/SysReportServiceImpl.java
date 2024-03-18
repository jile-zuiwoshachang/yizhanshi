package com.yizhanshi.system.service.impl;

import com.yizhanshi.system.api.domain.SysReport;
import com.yizhanshi.system.mapper.SysReportMapper;
import com.yizhanshi.system.service.ISysReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysReportServiceImpl implements ISysReportService {
    @Autowired
    private SysReportMapper reportMapper;

    /**
     * 查询举报信息
     *
     * @param reportId 举报ID
     * @return 举报信息
     */
    @Override
    public SysReport selectReportById(Long reportId)
    {
        return reportMapper.selectReportById(reportId);
    }

    /**
     * 查询举报列表
     *
     * @param report 举报信息
     * @return 举报集合
     */
    @Override
    public List<SysReport> selectReportList(SysReport report)
    {
        return reportMapper.selectReportList(report);
    }

    /**
     * 新增举报
     *
     * @param report 举报信息
     * @return 结果
     */
    @Override
    public int insertReport(SysReport report)
    {
        return reportMapper.insertReport(report);
    }

    /**
     * 修改举报
     *
     * @param report 举报信息
     * @return 结果
     */
    @Override
    public int updateReport(SysReport report)
    {
        return reportMapper.updateReport(report);
    }

    /**
     * 批量删除举报信息
     *
     * @param reportIds 需要删除的举报ID
     * @return 结果
     */
    @Override
    public int deleteReportByIds(Long[] reportIds)
    {
        return reportMapper.deleteReportByIds(reportIds);
    }
}
