package com.hsn.mall.core.request.update;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author huisunan
 * @date 2020/1/16 12:57
 */
@Data
public class AdminUpdateDTO implements Serializable {
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
     * 头像图片
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;
    /**
     * 角色ID
     */
    private List<Integer> roles;
}
