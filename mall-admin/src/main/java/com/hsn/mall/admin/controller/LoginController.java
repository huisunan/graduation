package com.hsn.mall.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.admin.annotation.LogExclude;
import com.hsn.mall.admin.annotation.Permission;
import com.hsn.mall.admin.bean.LoginUserBean;
import com.hsn.mall.admin.bean.ResponseResult;
import com.hsn.mall.admin.util.ResponseUtil;
import com.hsn.mall.admin.util.SubjectUtil;
import com.hsn.mall.core.request.LoginDTO;
import com.hsn.mall.admin.util.RequestUtil;
import com.hsn.mall.core.model.AdminModel;
import com.hsn.mall.core.service.IAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * 登录接口
 *
 * @author huisunan
 */
@RestController
@Permission("登录接口")
public class LoginController {
    @Reference
    private IAdminService adminService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 登录接口
     *
     * @param dto     登录dto
     * @param request 注入request
     * @return
     */
    @LogExclude
    @PostMapping("login")
    @Permission("登录")
    public LoginUserBean login(@RequestBody LoginDTO dto, HttpServletRequest request) {
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        //如果没认证进行认证
        if (!subject.isAuthenticated()) {
            if (dto.getRememberMe() != null) {
                token.setRememberMe(dto.getRememberMe());
            }
            subject.login(token);
        }
        //由于shiro缓存了认证信息，如果已认证了，不管本次认证是否为已认证用户都不再重新认证，
        // 所以需要判断本次登录认证的账号是否为已认证账号，如果不是则退出并重新认证
        if (subject.isAuthenticated()) {
            LoginUserBean loginUserBean = (LoginUserBean) subject.getPrincipal();
            if (!loginUserBean.getUsername().equals(dto.getUsername())) {
                subject.logout();
                subject.login(token);
            }
        }
        //重新获取认证信息并返回
        LoginUserBean loginUserBean = SubjectUtil.getUserBean();
        //修改登录信息
        AdminModel adminModel = new AdminModel();
        adminModel.setId(loginUserBean.getId());
        adminModel.setLastLoginIp(RequestUtil.getRemoteHost(request));
        adminModel.setLastLoginTime(LocalDateTime.now());
        adminService.updateById(adminModel);
        return loginUserBean;
    }

    /**
     * 登出
     */
    @GetMapping("logout")
    @LogExclude
    @Permission("登出")
    public ResponseResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseUtil.success("退出成功");
    }

    @LogExclude
    @GetMapping("toLogin")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult toLogin() {
        return ResponseUtil.success("请先登录!");
    }

    @GetMapping("userInfo")
    @Permission("获取用户信息")
    public LoginUserBean getUserInfo() {

        return SubjectUtil.getUserBean();
    }
}
