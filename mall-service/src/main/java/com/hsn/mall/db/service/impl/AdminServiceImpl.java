package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsn.mall.core.model.AdminModel;
import com.hsn.mall.db.mapper.AdminMapper;
import com.hsn.mall.core.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.sql.Wrapper;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IAdminService.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminModel> implements IAdminService {

    @Override
    public AdminModel getByUserName(String username) {
        QueryWrapper<AdminModel> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return this.getOne(wrapper);
    }
}
