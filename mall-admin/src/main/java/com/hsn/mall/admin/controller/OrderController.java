package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.OrderModel;
import com.hsn.mall.core.request.search.OrderSearchBean;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private IOrderService orderService;
    @PostMapping("/list")
    public PageResponse<Object> list(OrderSearchBean searchBean){
        Page<OrderModel> page = orderService.page(searchBean);
        return null;
    }
}

