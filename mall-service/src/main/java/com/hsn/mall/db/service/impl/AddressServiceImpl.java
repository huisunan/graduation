package com.hsn.mall.db.service.impl;

import com.hsn.mall.core.model.AddressModel;
import com.hsn.mall.db.mapper.AddressMapper;
import com.hsn.mall.core.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressModel> implements IAddressService {

}
