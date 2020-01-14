package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.PermissionModel;
import com.hsn.mall.db.mapper.PermissionMapper;
import com.hsn.mall.core.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IPermissionService.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionModel> implements IPermissionService {

}
