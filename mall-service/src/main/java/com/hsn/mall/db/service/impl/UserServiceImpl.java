package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.UserModel;
import com.hsn.mall.db.mapper.UserMapper;
import com.hsn.mall.core.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
