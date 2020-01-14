package com.hsn.mall.admin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huisunan
 * @date 2020/1/14 14:08
 */

@Data
@AllArgsConstructor
public class ResponseResult implements Serializable {
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;
}
