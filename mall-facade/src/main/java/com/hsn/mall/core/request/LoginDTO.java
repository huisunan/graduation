package com.hsn.mall.core.request;

import lombok.Data;

/**
 * @author huisnan
 */
@Data
public class LoginDTO {
    /**
     * 登录名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 记住我
     * */
    private Boolean rememberMe;
}
