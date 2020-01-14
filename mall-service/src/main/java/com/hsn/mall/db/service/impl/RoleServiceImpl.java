package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.RoleModel;
import com.hsn.mall.db.mapper.RoleMapper;
import com.hsn.mall.core.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IRoleService.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleModel> implements IRoleService {

}
