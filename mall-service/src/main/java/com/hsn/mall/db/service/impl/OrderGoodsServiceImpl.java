package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.OrderGoodsModel;
import com.hsn.mall.db.mapper.OrderGoodsMapper;
import com.hsn.mall.core.service.IOrderGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 订单商品表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IOrderGoodsService.class)
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoodsModel> implements IOrderGoodsService {

}
