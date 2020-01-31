package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.AdminModel;
import com.hsn.mall.core.model.PermissionModel;
import com.hsn.mall.core.request.create.AdminCreateDTO;
import com.hsn.mall.core.request.search.AdminSearchDTO;
import com.hsn.mall.core.request.update.AdminUpdateDTO;
import com.hsn.mall.db.mapper.AdminMapper;
import com.hsn.mall.core.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsn.mall.db.mapper.PermissionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        if (CollectionUtils.isNotEmpty(model.getRoleList())){
            List<String> roleIds = model.getRoleList().stream().map(i -> i.getId().toString()).collect(Collectors.toList());
            model.setPermissionList(getPermissionByRoleIds(roleIds));
        }
    }

    @Override
    public boolean save(AdminCreateDTO dto) {
        AdminModel model = new AdminModel();
        model.setLastLoginTime(LocalDateTime.now());
        BeanUtils.copyProperties(dto,model);
        save(model);
        if (CollectionUtils.isNotEmpty(dto.getRoles())){
            baseMapper.insertRole(model.getId(),dto.getRoles());
        }
        return true;
    }

    @Override
    public boolean updateById(AdminUpdateDTO dto) {
        AdminModel model = new AdminModel();
        BeanUtils.copyProperties(dto,model);
        if (dto.getRoles() != null){
           baseMapper.deleteAllRole(dto.getId());
           baseMapper.insertRole(dto.getId(),dto.getRoles());
        }
        return updateById(model);
    }

    @Override
    public Page<AdminModel> page(AdminSearchDTO dto) {
        QueryWrapper<AdminModel> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getKeyword())){
            queryWrapper.like("username",dto.getKeyword());
        }
        Page<AdminModel> page = new Page<>(dto.getPage(),dto.getLimit());
        return page(page,queryWrapper);
    }
}
