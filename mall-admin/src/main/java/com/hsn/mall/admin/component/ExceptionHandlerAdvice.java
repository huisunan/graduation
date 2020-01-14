package com.hsn.mall.admin.component;

import com.hsn.mall.admin.exception.BaseException;
import com.hsn.mall.admin.bean.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 * @author huisunan
 * @date 2020/1/14 14:10
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    /**
     * 处理业务异常BaseException
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(BaseException.class)
    public ResponseResult handleBaseException(BaseException e){
        log.error(e.getMessage(),e);
        return new ResponseResult(e.getCode(),e.getMessage(),null);
    }

    /**
     * 处理未捕获的RuntimeException
     * @param e 运行时异常
     * @return 统一响应体
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        String message = "";
        try {
            StackTraceElement ste = (new Throwable()).getStackTrace()[1];
            message = ste.getClassName()+":"+ste.getMethodName()+":" +ste.getLineNumber();
        } catch (Exception var3) {
        }
        return new ResponseResult(1,message,null);
    }

    /**
     * 处理未捕获的Exception
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e){
        log.error(e.getMessage(),e);
        String message = "";
        try {
            StackTraceElement ste = (new Throwable()).getStackTrace()[1];
            message = ste.getClassName()+":"+ste.getMethodName()+":" +ste.getLineNumber();
        } catch (Exception var3) {
        }
        return new ResponseResult(1,message,null);
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseResult handleException(AuthorizationException e, Model model) {

        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)
        log.debug("AuthorizationException was thrown", e);
        return new ResponseResult(HttpStatus.FORBIDDEN.value(),"拒绝访问!",null);
    }
}
