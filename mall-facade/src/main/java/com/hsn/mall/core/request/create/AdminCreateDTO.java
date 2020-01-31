package com.hsn.mall.core.request.create;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author huisunan
 */
@Data
public class AdminCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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

    /**
     * 角色ID
     */
    private List<Integer> roles;
}
