package com.yizhanshi.place.api.domain;

import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.annotation.Excel.ColumnType;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.common.core.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 场地信息表
 *
 * @author hejiale
 */
public class Place extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 场地序号 */
    @Excel(name = "场地序号", cellType = ColumnType.NUMERIC)
    private Long placeId;

    /** 场地名称 */
    @Excel(name = "场地名称")
    private String placeName;

    /** 场地位置（校区） */
    @Excel(name = "场地位置（校区）")
    private String placeCampus;
    @Excel(name = "场地描述")
    private String placeDescription;
    @Excel(name = "场地容量")
    private int placeCapacity;
    @Excel(name = "管理者姓名")
    private String managerName;
    @Excel(name = "管理者电话")
    private String managerPhone;
    @Excel(name = "图片1")
    private String placePicture1;
    @Excel(name = "图片2")
    private String placePicture2;
    @Excel(name = "图片3")
    private String placePicture3;
    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;



    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
    @Xss(message = "场地名称不能包含脚本字符")
    @NotBlank(message = "场地名称不能为空")
    @Size(min = 0, max = 100, message = "场地名称长度不能超过100个字符")
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceCampus() {
        return placeCampus;
    }

    public void setPlaceCampus(String placeCampus) {
        this.placeCampus = placeCampus;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public int getPlaceCapacity() {
        return placeCapacity;
    }

    public void setPlaceCapacity(int placeCapacity) {
        this.placeCapacity = placeCapacity;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    @Size(min = 0, max = 11, message = "用户电话长度不能超过11个字符")
    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getPlacePicture1() {
        return placePicture1;
    }

    public void setPlacePicture1(String placePicture1) {
        this.placePicture1 = placePicture1;
    }

    public String getPlacePicture2() {
        return placePicture2;
    }

    public void setPlacePicture2(String placePicture2) {
        this.placePicture2 = placePicture2;
    }

    public String getPlacePicture3() {
        return placePicture3;
    }

    public void setPlacePicture3(String placePicture3) {
        this.placePicture3 = placePicture3;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("placeId", placeId)
                .append("placeName", placeName)
                .append("placeCampus", placeCampus)
                .append("placeDescription", placeDescription)
                .append("placeCapacity", placeCapacity)
                .append("managerName", managerName)
                .append("managerPhone", managerPhone)
                .append("placePicture1", placePicture1)
                .append("placePicture2", placePicture2)
                .append("placePicture3", placePicture3)
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
