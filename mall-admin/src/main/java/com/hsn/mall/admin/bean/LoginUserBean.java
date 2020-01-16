package com.hsn.mall.admin.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hsn.mall.core.model.PermissionModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author huisunan
 * @date 2020/1/14 11:52
 */
@Data
public class LoginUserBean  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 管理员名称
     */
    private String username;

    /**
     * 最近一次登录IP地址
     */
    private String lastLoginIp;

    /**
     * 最近一次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 头像图片
     */
    private String avatar;

    /**
     * 0:启用 1：禁用
     */
    private Boolean disabled;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 角色代码列表
     */
    private String roleIds;

    /**
     * 权限列表
     */
    private List<PermissionModel> permissionList;

    /**
     * token
     */
    private String token;
}
