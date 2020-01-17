package com.hsn.mall.admin.util;

import com.hsn.mall.admin.bean.LoginUserBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SubjectUtil {
    public static Integer getUserId(){
        return getUserBean().getId();
    }

    public static LoginUserBean getUserBean(){
        Subject subject = SecurityUtils.getSubject();
        LoginUserBean principal = (LoginUserBean) subject.getPrincipal();
        principal.setToken(subject.getSession().getId().toString());
        return principal;
    }

    public static String getUserName(){
        return getUserBean().getUsername();
    }
}
