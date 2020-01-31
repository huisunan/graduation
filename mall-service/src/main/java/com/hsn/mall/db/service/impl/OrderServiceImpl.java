package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.OrderModel;
import com.hsn.mall.core.request.search.OrderSearchBean;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.OrderMapper;
import com.hsn.mall.core.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IOrderService.class)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderModel> implements IOrderService {

    @Override
    public Page<OrderModel> page(OrderSearchBean searchBean) {
        QueryWrapper<OrderModel> query = new QueryWrapper<>();
        if (searchBean.getUserId() != null){
            query.eq("user_id",searchBean.getUserId());
        }
        if (StringUtils.isNotBlank(searchBean.getOrderSn())){
            query.like("order_sn",searchBean.getOrderSn());
        }
        if (!CollectionUtils.isEmpty(searchBean.getOrderStatusArray())){
            query.in("order_status",searchBean.getOrderStatusArray());
        }
        return page(PageUtil.create(searchBean),query);
    }
}
