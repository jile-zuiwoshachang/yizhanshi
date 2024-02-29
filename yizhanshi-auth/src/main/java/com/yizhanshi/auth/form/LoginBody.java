package com.yizhanshi.auth.form;

/**
 * 用户登录对象
 * 
 * @author hejiale
 */
public class LoginBody
{
    /**
     * 学号
     */
    private String userStudentid;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
