package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.AddressModel;
import com.hsn.mall.db.mapper.AddressMapper;
import com.hsn.mall.core.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IAddressService.class)
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressModel> implements IAddressService {

}
