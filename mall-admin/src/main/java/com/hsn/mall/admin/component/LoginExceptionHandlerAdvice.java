package com.hsn.mall.admin.component;

import com.hsn.mall.admin.bean.ResponseResult;
import com.hsn.mall.admin.exception.BaseException;
import com.hsn.mall.admin.exception.NoPermissionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 登录异常处理器
 */
@RestControllerAdvice
@Slf4j
public class LoginExceptionHandlerAdvice {
    /**
     * 处理UnknownAccountException
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(UnknownAccountException.class)
    public ResponseResult handleUnknownAccountException(UnknownAccountException e){
        log.info(e.getMessage());
        return new ResponseResult(1,"用户不存在",null);
    }
    /**
     * 处理IncorrectCredentialsException
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseResult handleIncorrectCredentialsException(IncorrectCredentialsException e){
        log.debug(e.getMessage());
        return new ResponseResult(1,"密码错误",null);
    }

    /**
     * 处理LockedAccountException
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(LockedAccountException.class)
    public ResponseResult handleLockedAccountException (LockedAccountException e){
        log.debug(e.getMessage());
        return new ResponseResult(1,"账号被锁定",null);
    }

    /**
     * 处理LockedAccountException
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(NoPermissionException.class)
    public ResponseResult handleNoPermissionException(NoPermissionException  e){
        log.debug(e.getMessage());
        return new ResponseResult(1,"权限不足",null);
    }


    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseResult handleAuthorizationException(AuthorizationException e) {

        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        log.debug("AuthorizationException was thrown", e);
        return new ResponseResult(HttpStatus.FORBIDDEN.value(),"拒绝访问!",null);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult handleAuthenticationException(AuthenticationException e) {

        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        log.debug("AuthenticationException was thrown", e);
        return new ResponseResult(HttpStatus.FORBIDDEN.value(),"请先登录!",null);
    }
}
