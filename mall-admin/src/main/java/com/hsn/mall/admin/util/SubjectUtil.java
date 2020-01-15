package com.hsn.mall.admin.util;

import com.hsn.mall.admin.bean.LoginUserBean;
import org.apache.shiro.SecurityUtils;

public class SubjectUtil {
    public static Integer getUserId(){
        return getUserBean().getId();
    }

    public static LoginUserBean getUserBean(){
        return (LoginUserBean) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserName(){
        return getUserBean().getUsername();
    }
}
