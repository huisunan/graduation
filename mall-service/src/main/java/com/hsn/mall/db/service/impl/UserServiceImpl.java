package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.UserModel;
import com.hsn.mall.core.request.UserSearchBean;
import com.hsn.mall.db.mapper.UserMapper;
import com.hsn.mall.core.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IUserService.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements IUserService {

    @Override
    public Page<UserModel> page(UserSearchBean search) {
        QueryWrapper<UserModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(search.getKeyword())){
            query.like("username",search.getKeyword());
        }
        if (StringUtils.isNotBlank(search.getPhone())){
            query.eq("mobile",search.getPhone());
        }
        Page<UserModel> page = new Page<>();
        BeanUtils.copyProperties(search,page);
        return page(page,query);
    }
}
