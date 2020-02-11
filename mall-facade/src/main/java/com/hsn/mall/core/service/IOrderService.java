package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.ResponseResult;
import com.hsn.mall.core.model.OrderModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.request.common.OrderRefundDTO;
import com.hsn.mall.core.request.common.OrderReplyDTO;
import com.hsn.mall.core.request.common.OrderShipDTO;
import com.hsn.mall.core.request.search.OrderSearchBean;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IOrderService extends IService<OrderModel> {
    Page<OrderModel> page(OrderSearchBean searchBean);

    ResponseResult refund(OrderRefundDTO dto);

    ResponseResult ship(OrderShipDTO dto);

    ResponseResult reply(OrderReplyDTO dto);
}
