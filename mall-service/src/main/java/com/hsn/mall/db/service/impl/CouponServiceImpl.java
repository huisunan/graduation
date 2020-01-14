package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.CouponModel;
import com.hsn.mall.db.mapper.CouponMapper;
import com.hsn.mall.core.service.ICouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 优惠券信息及规则表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ICouponService.class)
public class CouponServiceImpl extends ServiceImpl<CouponMapper, CouponModel> implements ICouponService {

}
