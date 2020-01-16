package com.hsn.mall.admin.util;

import com.hsn.mall.admin.bean.ResponseResult;

public class ResponseUtil {
    public static ResponseResult fail(String message){
        return fail(message,null);
    }
    public static ResponseResult fail(String message,Object data){
        return new ResponseResult(1,message,data);
    }
    public static ResponseResult success(String message){
        return success(message,null);
    }
    public static ResponseResult success(String message,Object data){
        return new ResponseResult(0,message,data);
    }
    public static ResponseResult newInstance(Integer code,String message,Object data){
        return new ResponseResult(code,message,data);
    }
}
