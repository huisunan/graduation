package com.hsn.mall.core.service;

import com.hsn.mall.core.model.AdminModel;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
