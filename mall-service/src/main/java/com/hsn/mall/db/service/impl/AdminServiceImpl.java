package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hsn.mall.core.model.AdminModel;
import com.hsn.mall.core.model.PermissionModel;
import com.hsn.mall.core.service.IPermissionService;
import com.hsn.mall.db.mapper.AdminMapper;
import com.hsn.mall.core.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsn.mall.db.mapper.PermissionMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.naming.factory.LookupFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public AdminModel getByUserName(String username) {
        QueryWrapper<AdminModel> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        AdminModel model = getOne(wrapper);
        if (model != null){
            setPermission(model);
        }
        return model;
    }

    @Override
    public List<PermissionModel> getPermissionByRoleIds(List<String> roles) {
        return permissionMapper.selectByRoleIds(roles);
    }

    private void setPermission(AdminModel model){
        if(StringUtils.isNotEmpty(model.getRoleIds())){
            List<String> ids = Arrays.asList(model.getRoleIds().split(","));
            model.setPermissionList(permissionMapper.selectByRoleIds(ids));
        }
    }

}
