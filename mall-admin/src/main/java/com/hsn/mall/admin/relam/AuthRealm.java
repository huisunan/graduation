package com.hsn.mall.admin.relam;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.spring.boot.DubboProperties;
import com.hsn.mall.admin.bean.LoginUserBean;
import com.hsn.mall.admin.exception.NoPermissionException;
import com.hsn.mall.core.model.AdminModel;
import com.hsn.mall.core.service.IAdminService;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author huisunan
 * @date 2020/1/14 11:31
 */
@Component
@Slf4j
public class AuthRealm extends AuthorizingRealm {
    private IAdminService iAdminService;

    @Autowired
    private DubboProperties dubboProperties;

    public void setService(){
        ReferenceConfig<IAdminService> adminServiceReferenceConfig = new ReferenceConfig<>();
        adminServiceReferenceConfig.setApplication(dubboProperties.getApplication());
        adminServiceReferenceConfig.setRegistry(dubboProperties.getRegistry());
        adminServiceReferenceConfig.setCheck(false);
        adminServiceReferenceConfig.setInterface(IAdminService.class);
        iAdminService = adminServiceReferenceConfig.get();
    }

    /**
     *授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1. 从 PrincipalCollection 中来获取登录用户的信息
        Object principal = principals.getPrimaryPrincipal();
        LoginUserBean loginUserBean = (LoginUserBean) principal;
        // 2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
        Set<String> roles = new HashSet<>();
        if (StringUtils.isNotEmpty(loginUserBean.getRoleIds())) {
            roles = new HashSet<>(Arrays.asList(loginUserBean.getRoleIds().split(",")));
        }

        // 3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        // 4. 返回 SimpleAuthorizationInfo 对象.
        return info;
    }

    /**
     *认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("AuthRealm开始认证！");
        // 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        // 2. 从 UsernamePasswordToken 中来获取 username
        String username = upToken.getUsername();

        // 3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        AdminModel userModel = iAdminService.getByUserName(username);

        // 4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (userModel == null) {
            throw new UnknownAccountException("用户不存在!");
        }

        // 5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
        if (userModel.getDisabled()) {
            throw new LockedAccountException("用户被锁定");
        }

        // 6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo

        // 以下信息是从数据库中获取的.
        // 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        LoginUserBean loginUser = new LoginUserBean();
        BeanUtils.copyProperties(userModel, loginUser);

        //用户拥有的角色
        String[] roles = StringUtils.split(userModel.getRoleIds());
        if (roles.length == 0) {
            throw new NoPermissionException("没有权限登录");
        }


        Object principal = loginUser;
        // 2). credentials: 密码.
        Object credentials = userModel.getPassword();
        // 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();
        // 4). ByteSource credentialsSalt 盐值，相同的密码，加密之后密文是一样的，加入盐值使每个人的密码都不一样
        ByteSource salt = ByteSource.Util.bytes(StringUtils.reverse(userModel.getUsername()));

        // SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);
        log.info("AuthRealm 结束认证！");
        return info;
    }
}
