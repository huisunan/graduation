package com.hsn.mall.db.service.impl;

import com.hsn.mall.core.model.OrderModel;
import com.hsn.mall.db.mapper.OrderMapper;
import com.hsn.mall.core.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderModel> implements IOrderService {

}
