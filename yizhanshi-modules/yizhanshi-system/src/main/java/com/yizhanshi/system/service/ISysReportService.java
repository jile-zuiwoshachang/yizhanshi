package com.yizhanshi.system.service;


import com.yizhanshi.system.api.domain.SysReport;
import java.util.List;

/**
 * 举报 服务层
 *
 * @author hejiale
 */
public interface ISysReportService {
    /**
     * 查询举报信息
     *
     * @param reportId 举报ID
     * @return 举报信息
     */
    public SysReport selectReportById(Long reportId);

    /**
     * 查询举报列表
     *
     * @param report 举报信息
     * @return 举报集合
     */
    public List<SysReport> selectReportList(SysReport report);

    /**
     * 新增举报
     *
     * @param report 举报信息
     * @return 结果
     */
    public int insertReport(SysReport report);

    /**
     * 修改举报
     *
     * @param report 举报信息
     * @return 结果
     */
    public int updateReport(SysReport report);

    /**
     * 批量删除举报信息
     *
     * @param reportIds 需要删除的举报ID
     * @return 结果
     */
    public int deleteReportByIds(Long[] reportIds);
}
