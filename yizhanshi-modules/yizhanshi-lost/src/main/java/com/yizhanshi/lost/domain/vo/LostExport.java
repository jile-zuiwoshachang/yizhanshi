package com.yizhanshi.lost.domain.vo;

import com.yizhanshi.common.core.annotation.Excel;

import java.util.Date;

public class LostExport {
    private static final long serialVersionUID = 1L;
    @Excel(name = "失物序号", cellType = Excel.ColumnType.NUMERIC)
    private Long lostId;
    @Excel(name = "失物名称")
    private String lostName;
    @Excel(name = "失物简介")
    private String lostDescription;
    @Excel(name = "失物图片")
    private String lostPicture;
    @Excel(name = "失物类型")
    private String lostType;
    @Excel(name = "捡拾地点")
    private String pickPlace;
    @Excel(name = "捡拾校区",readConverterExp = "0=北校区,1=南校区")
    private String pickCampus;
    @Excel(name = "捡拾日期")
    private Date pickDay;
    @Excel(name = "捡拾时间",dateFormat = "yyyy-MM-dd")
    private String pickTime;
    @Excel(name = "捡拾人序号")
    private String pickStudentid;
    @Excel(name = "捡拾人姓名")
    private String pickName;
    @Excel(name = "捡拾后暂存地点")
    private String pickStorePlace;
    @Excel(name = "状态",readConverterExp = "0=未认领,1=已认领")
    private String status;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "创建人")
    private String createBy;
    @Excel(name = "备注")
    private String remark;
    public LostExport(){}
    public LostExport(Long lostId, String lostName, String lostDescription, String lostPicture, String lostType, String pickPlace, String pickCampus, Date pickDay, String pickTime, String pickStudentid, String pickName, String pickStorePlace, String status, Date createTime, String createBy, String remark) {
        this.lostId = lostId;
        this.lostName = lostName;
        this.lostDescription = lostDescription;
        this.lostPicture = lostPicture;
        this.lostType = lostType;
        this.pickPlace = pickPlace;
        this.pickCampus = pickCampus;
        this.pickDay = pickDay;
        this.pickTime = pickTime;
        this.pickStudentid = pickStudentid;
        this.pickName = pickName;
        this.pickStorePlace = pickStorePlace;
        this.status = status;
        this.createTime = createTime;
        this.createBy = createBy;
        this.remark = remark;
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

    public String getLostDescription() {
        return lostDescription;
    }

    public void setLostDescription(String lostDescription) {
        this.lostDescription = lostDescription;
    }

    public String getLostPicture() {
        return lostPicture;
    }

    public void setLostPicture(String lostPicture) {
        this.lostPicture = lostPicture;
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
