package com.yizhanshi.system.api.domain;


import java.util.List;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yizhanshi.common.core.annotation.Excel;
import com.yizhanshi.common.core.annotation.Excel.ColumnType;
import com.yizhanshi.common.core.annotation.Excel.Type;
import com.yizhanshi.common.core.web.domain.BaseEntity;
import com.yizhanshi.common.core.xss.Xss;

/**
 * 用户对象 sys_user
 *
 * @author yizhanshi
 */

public class SysUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /** 学号 */
    @Excel(name = "用户学号", type = Type.EXPORT)
    private String userStudentid;
    /** 学号 */
    @Excel(name = "用户性别，0男 1女 2未知")
    private String userSex;
    /** 学号 */
    @Excel(name = "部门id")
    private Long deptId;

    /** 用户姓名 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户学院 */
    @Excel(name = "用户学院")
    private String userOrganization;
    /** 用户学院 */
    @Excel(name = "所在校区",readConverterExp = "北校区 南校区")
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

    /** 用户评价分数*/
    @Excel(name = "用户评价分数(满分100)")
    private int userScore;
    /** 用户简介 */
    @Excel(name = "用户简介")
    private String userDescription;
    /** 用户图片1 */
    private String userPicture1;
    /** 用户图片2 */
    private String userPicture2;
    /** 用户图片3 */
    private String userPicture3;

    /** 密码 */
    private String userPassword;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 角色对象 */
    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 角色ID  用于查询数据和/authUser/allocatedList使用 */
    private Long roleId;

    public SysUser()
    {

    }

    public SysUser(Long userId)
    {
        this.userId = userId;
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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    //is开头的方法，后端会进行处理，去掉is并认为这是一个布尔值并返回给前端，即adminByType和admin字段，哪怕你没有定义他们
    public boolean isAdminByType()
    {
        return isAdminByType(this.userType);
    }
    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public static boolean isAdminByType(String userType)
    {
        return "00".equals(userType);
    }
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 123456789L == userId;
    }



    @Xss(message = "用户姓名不能包含脚本字符")
    @NotBlank(message = "用户姓名不能为空")
    @Size(min = 0, max = 100, message = "用户姓名长度不能超过100个字符")
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 30, message = "邮箱长度不能超过30个字符")
    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String email)
    {
        this.userEmail = email;
    }

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String phonenumber)
    {
        this.userPhone = phonenumber;
    }

    public String getUserStudentid() {
        return userStudentid;
    }

    public void setUserStudentid(String userStudentid) {
        this.userStudentid = userStudentid;
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

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String password)
    {
        this.userPassword = password;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public List<SysRole> getRoles()
    {
        return roles;
    }

    public void setRoles(List<SysRole> roles)
    {
        this.roles = roles;
    }

    public Long[] getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds)
    {
        this.roleIds = roleIds;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("userStudentid", getUserStudentid())
                .append("userSex", getUserSex())
                .append("deptId", getDeptId())
                .append("userPassword", getUserPassword())
                .append("userOrganization", getUserOrganization())
                .append("userCampus", getUserCampus())
                .append("userName", getUserName())
                .append("userType", getUserType())
                .append("userEmail", getUserEmail())
                .append("userPhone", getUserPhone())
                .append("userScore", getUserScore())
                .append("userDescription", getUserDescription())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }



}
