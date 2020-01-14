package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.SystemModel;
import com.hsn.mall.db.mapper.SystemMapper;
import com.hsn.mall.core.service.ISystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ISystemService.class)
public class SystemServiceImpl extends ServiceImpl<SystemMapper, SystemModel> implements ISystemService {

}
