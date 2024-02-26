package com.yizhanshi.system.controller;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.utils.StringUtils;
import com.yizhanshi.common.core.utils.file.FileTypeUtils;
import com.yizhanshi.common.core.utils.file.MimeTypeUtils;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.service.TokenService;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.system.api.RemoteFileService;
import com.yizhanshi.system.api.domain.SysFile;
import com.yizhanshi.system.api.domain.SysUser;
import com.yizhanshi.system.api.model.LoginUser;
import com.yizhanshi.system.service.ISysUserService;

/**
 * 个人信息 业务处理
 * 
 * @author hejiale
 */
@RestController
@RequestMapping("/user/profile")
public class SysProfileController extends BaseController
{
    @Autowired
    private ISysUserService userService;
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile()
    {
        String userStudentid= SecurityUtils.getUserStudentid();
        SysUser user = userService.selectUserByUserStudentid(userStudentid);
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(userStudentid));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser currentUser = loginUser.getSysUser();
        currentUser.setUserSex(user.getUserSex());
        currentUser.setUserCampus(user.getUserCampus());
        currentUser.setUserDescription(user.getUserDescription());
        currentUser.setUserPicture1(user.getUserPicture1());
        currentUser.setUserPicture2(user.getUserPicture2());
        currentUser.setUserPicture3(user.getUserPicture3());
        currentUser.setUserEmail(user.getUserEmail());
        currentUser.setUserPhone(user.getUserPhone());
        currentUser.setUserOrganization(user.getUserOrganization());
        if (StringUtils.isNotEmpty(user.getUserPhone()) && !userService.checkPhoneUnique(currentUser))
        {
            return error("修改用户'" + loginUser.getUsername() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getUserEmail()) && !userService.checkEmailUnique(currentUser))
        {
            return error("修改用户'" + loginUser.getUsername() + "'失败，邮箱账号已存在");
        }
        if (userService.updateUserProfile(currentUser) > 0)
        {
            // 更新缓存用户信息
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword)
    {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        String password = user.getUserPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return error("新密码不能与旧密码相同");
        }
        newPassword = SecurityUtils.encryptPassword(newPassword);
        if (userService.resetUserPwd(username, newPassword) > 0)
        {
            // 更新缓存用户密码
            LoginUser loginUser = SecurityUtils.getLoginUser();
            loginUser.getSysUser().setUserPassword(newPassword);
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }
    

}
