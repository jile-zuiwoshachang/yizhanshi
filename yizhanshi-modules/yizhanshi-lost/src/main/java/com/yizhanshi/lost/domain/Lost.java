package com.yizhanshi.lost.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.system.api.domain.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Lost extends BaseEntity {
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
    @Excel(name = "状态",readConverterExp = "0=未认领,1=已认领")
    private String status;
    private String delFlag;
    private SysUser pick;
    public Lost(){}
    public Lost(Long lostId, String lostName, String lostDescription, String lostPicture, String lostType, String pickPlace, String pickCampus, Date pickDay, String pickTime, String pickStudentid, String pickName, String pickStorePlace, String status, String delFlag) {
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
        this.delFlag = delFlag;
    }

    public SysUser getPick() {
        return pick;
    }

    public void setPick(SysUser pick) {
        this.pick = pick;
    }

    public Long getLostId() {
        return lostId;
    }

    public void setLostId(Long lostId) {
        this.lostId = lostId;
    }
    @NotNull(message = "失物名称不能为空")
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
    @NotNull(message = "捡拾校区不能为空")
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("lostId", lostId)
                .append("lostName", lostName)
                .append("lostDescription", lostDescription)
                .append("lostPicture", lostPicture)
                .append("lostType", lostType)
                .append("pickPlace", pickPlace)
                .append("pickCampus", pickCampus)
                .append("pickDay", pickDay)
                .append("pickTime", pickTime)
                .append("pickStudentid", pickStudentid)
                .append("pickName", pickName)
                .append("pickStorePlace", pickStorePlace)
                .append("status", status)
                .append("delFlag", delFlag)
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
