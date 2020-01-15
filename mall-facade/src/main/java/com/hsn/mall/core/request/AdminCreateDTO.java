package com.hsn.mall.core.request;

import lombok.Data;


@Data
public class AdminCreateDTO {
    /**
     * 管理员名称
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 头像图片
     */
    private String avatar;

    /**
     * 0:启用 1：禁用
     */
    private Boolean disabled;
}
