package com.hsn.mall.db.service.impl;

import com.hsn.mall.core.model.AdminModel;
import com.hsn.mall.db.mapper.AdminMapper;
import com.hsn.mall.core.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminModel> implements IAdminService {

}
