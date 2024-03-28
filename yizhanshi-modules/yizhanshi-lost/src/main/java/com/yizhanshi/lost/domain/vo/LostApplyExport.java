package com.yizhanshi.lost.domain.vo;

import com.yizhanshi.common.core.annotation.Excel;

import java.util.Date;

public class LostApplyExport {

    private static final long serialVersionUID = 1L;
    @Excel(name = "失物认领序号", cellType = Excel.ColumnType.NUMERIC)
    private Long applyId;
    @Excel(name = "认领人名称")
    private String applyName;
    @Excel(name = "认领人学号")
    private String userStudentid;
    @Excel(name = "认领原因或者内容")
    private String applyContent;
    @Excel(name = "失物序号", cellType = Excel.ColumnType.NUMERIC)
    private Long lostId;
    @Excel(name = "失物名称")
    private String lostName;
    @Excel(name = "失物类型")
    private String lostType;
    @Excel(name = "捡拾地点")
    private String pickPlace;
    @Excel(name = "捡拾校区",readConverterExp = "0=北校区,1=南校区")
    private String pickCampus;
    @Excel(name = "捡拾日期",dateFormat = "yyyy-MM-dd")
    private Date pickDay;
    @Excel(name = "捡拾时间")
    private String pickTime;
    @Excel(name = "捡拾人序号")
    private String pickStudentid;
    @Excel(name = "捡拾人姓名")
    private String pickName;
    @Excel(name = "捡拾后暂存地点")
    private String pickStorePlace;
    @Excel(name = "拒绝原因")
    private String refuseReason;
    @Excel(name = "状态",readConverterExp = "0=已申请,2=已同意,5=已拒绝")
    private String status;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "创建人")
    private String createBy;
    @Excel(name = "备注")
    private String remark;
    public LostApplyExport(){}
    public LostApplyExport(Long applyId, String applyName, String userStudentid, String applyContent, Long lostId, String lostName, String lostType, String pickPlace, String pickCampus, Date pickDay, String pickTime, String pickStudentid, String pickName, String pickStorePlace, String refuseReason, String status, Date createTime, String createBy, String remark) {
        this.applyId = applyId;
        this.applyName = applyName;
        this.userStudentid = userStudentid;
        this.applyContent = applyContent;
        this.lostId = lostId;
        this.lostName = lostName;
        this.lostType = lostType;
        this.pickPlace = pickPlace;
        this.pickCampus = pickCampus;
        this.pickDay = pickDay;
        this.pickTime = pickTime;
        this.pickStudentid = pickStudentid;
        this.pickName = pickName;
        this.pickStorePlace = pickStorePlace;
        this.refuseReason = refuseReason;
        this.status = status;
        this.createTime = createTime;
        this.createBy = createBy;
        this.remark = remark;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getUserStudentid() {
        return userStudentid;
    }

    public void setUserStudentid(String userStudentid) {
        this.userStudentid = userStudentid;
    }

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }

    public Long getLostId() {
        return lostId;
    }

    public void setLostId(Long lostId) {
        this.lostId = lostId;
    }

    public String getLostName() {
        return lostName;
    }

    public void setLostName(String lostName) {
        this.lostName = lostName;
    }

    public String getLostType() {
        return lostType;
    }

    public void setLostType(String lostType) {
        this.lostType = lostType;
    }

    public String getPickPlace() {
        return pickPlace;
    }

    public void setPickPlace(String pickPlace) {
        this.pickPlace = pickPlace;
    }

    public String getPickCampus() {
        return pickCampus;
    }

    public void setPickCampus(String pickCampus) {
        this.pickCampus = pickCampus;
    }

    public Date getPickDay() {
        return pickDay;
    }

    public void setPickDay(Date pickDay) {
        this.pickDay = pickDay;
    }

    public String getPickTime() {
        return pickTime;
    }

    public void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }

    public String getPickStudentid() {
        return pickStudentid;
    }

    public void setPickStudentid(String pickStudentid) {
        this.pickStudentid = pickStudentid;
    }

    public String getPickName() {
        return pickName;
    }

    public void setPickName(String pickName) {
        this.pickName = pickName;
    }

    public String getPickStorePlace() {
        return pickStorePlace;
    }

    public void setPickStorePlace(String pickStorePlace) {
        this.pickStorePlace = pickStorePlace;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
