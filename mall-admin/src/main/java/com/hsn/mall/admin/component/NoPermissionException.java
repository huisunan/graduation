package com.hsn.mall.admin.component;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author huisunan
 * @date 2020/1/14 12:00
 */
public class NoPermissionException extends AuthenticationException {
    public NoPermissionException(String message) {
        super(message);
    }
}
