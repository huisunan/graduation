package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.RolePermissionModel;
import com.hsn.mall.db.mapper.RolePermissionMapper;
import com.hsn.mall.core.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IRolePermissionService.class)
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionModel> implements IRolePermissionService {

}
