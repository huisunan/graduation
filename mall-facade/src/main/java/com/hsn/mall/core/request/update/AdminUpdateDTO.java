package com.hsn.mall.core.request.update;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author huisunan
 * @date 2020/1/16 12:57
 */
@Data
public class AdminUpdateDTO {
    /**
     * 用户ID
     */
    @NotNull(message = "id不能为空")
    private Integer id;
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
