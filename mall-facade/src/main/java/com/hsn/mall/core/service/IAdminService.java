package com.hsn.mall.core.service;

import com.hsn.mall.core.model.AdminModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.model.PermissionModel;

import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IAdminService extends IService<AdminModel> {
    /**
     * 通过用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    AdminModel getByUserName(String username);

    /**
     * 根据角色ids获取权限
     * @param roles 角色id
     * @return 权限列表
     */
    List<PermissionModel> getPermissionByRoleIds(List<String> roles);
}
