package com.yizhanshi.talent.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


public class TalentExport {
    private static final long serialVersionUID = 1L;
    /** 用户ID */
    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;
    /** 学号 */
    @Excel(name = "用户学号", type = Excel.Type.EXPORT)
    private String userStudentid;
    /** 学号 */
    @Excel(name = "用户性别",readConverterExp = "0=男,1=1女,2=未知")
    private String userSex;
    /** 用户姓名 */
    @Excel(name = "用户名称")
    private String userName;
    /** 用户学院 */
    @Excel(name = "用户学院")
    private String userOrganization;
    /** 用户校区 */
    @Excel(name = "所在校区",readConverterExp = "0=北校区,1=南校区")
    private String userCampus;
    /** 用户类型 */
    @Excel(name = "用户类型", readConverterExp = "01=普通,02=管理,03=系统")
    private String userType;
    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String userEmail;
    /** 手机号码 */
    @Excel(name = "手机号码")
    private String userPhone;
    /** 用户qq */
    @Excel(name = "用户qq")
    private String userQq;
    /** 用户微信 */
    @Excel(name = "用户微信号")
    private String userWechat;
    /** 用户评价分数*/
    @Excel(name = "用户评价分数(满分100)")
    private int userScore;
    /** 用户简介 */
    @Excel(name = "用户简介")
    private String userDescription;
    /** 用户图片1 */
    @Excel(name = "用户图片1")
    private String userPicture1;
    /** 用户图片2 */
    @Excel(name = "用户图片2")
    private String userPicture2;
    /** 用户图片3 */
    @Excel(name = "用户图片3")
    private String userPicture3;
    @Excel(name = "标签")
     private String lableNames;
    @Excel(name = "创建时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "创建人")
    private String createBy;
    @Excel(name = "备注")
    private String remark;
    public TalentExport(){}
    public TalentExport(Long userId, String userStudentid, String userSex, String userName, String userOrganization, String userCampus, String userType, String userEmail, String userPhone, String userQq, String userWechat, int userScore, String userDescription, String userPicture1, String userPicture2, String userPicture3, String lableNames, Date createTime, String createBy, String remark) {
        this.userId = userId;
        this.userStudentid = userStudentid;
        this.userSex = userSex;
        this.userName = userName;
        this.userOrganization = userOrganization;
        this.userCampus = userCampus;
        this.userType = userType;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userQq = userQq;
        this.userWechat = userWechat;
        this.userScore = userScore;
        this.userDescription = userDescription;
        this.userPicture1 = userPicture1;
        this.userPicture2 = userPicture2;
        this.userPicture3 = userPicture3;
        this.lableNames = lableNames;
        this.createTime = createTime;
        this.createBy = createBy;
        this.remark = remark;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }


    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserStudentid() {
        return userStudentid;
    }

    public void setUserStudentid(String userStudentid) {
        this.userStudentid = userStudentid;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOrganization() {
        return userOrganization;
    }

    public void setUserOrganization(String userOrganization) {
        this.userOrganization = userOrganization;
    }

    public String getUserCampus() {
        return userCampus;
    }

    public void setUserCampus(String userCampus) {
        this.userCampus = userCampus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public String getUserWechat() {
        return userWechat;
    }

    public void setUserWechat(String userWechat) {
        this.userWechat = userWechat;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserPicture1() {
        return userPicture1;
    }

    public void setUserPicture1(String userPicture1) {
        this.userPicture1 = userPicture1;
    }

    public String getUserPicture2() {
        return userPicture2;
    }

    public void setUserPicture2(String userPicture2) {
        this.userPicture2 = userPicture2;
    }

    public String getUserPicture3() {
        return userPicture3;
    }

    public void setUserPicture3(String userPicture3) {
        this.userPicture3 = userPicture3;
    }

    public String getLableNames() {
        return lableNames;
    }

    public void setLableNames(String lableNames) {
        this.lableNames = lableNames;
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
