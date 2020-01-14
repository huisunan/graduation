package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.CouponUserModel;
import com.hsn.mall.db.mapper.CouponUserMapper;
import com.hsn.mall.core.service.ICouponUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 优惠券用户使用表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ICouponUserService.class)
public class CouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUserModel> implements ICouponUserService {

}
