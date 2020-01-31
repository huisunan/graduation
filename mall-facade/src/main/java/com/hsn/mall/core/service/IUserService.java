package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.UserModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.request.UserSearchBean;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IUserService extends IService<UserModel> {
    Page<UserModel> page(UserSearchBean search);
}
