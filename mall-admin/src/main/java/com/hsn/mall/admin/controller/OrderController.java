package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.ResponseResult;
import com.hsn.mall.core.express.ExpressService;
import com.hsn.mall.core.model.OrderModel;
import com.hsn.mall.core.request.common.OrderRefundDTO;
import com.hsn.mall.core.request.common.OrderReplyDTO;
import com.hsn.mall.core.request.common.OrderShipDTO;
import com.hsn.mall.core.request.search.OrderSearchBean;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IOrderService;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private ExpressService expressService;
    @PostMapping("/list")
    public PageResponse<OrderModel> list(OrderSearchBean searchBean){
        Page<OrderModel> page = orderService.page(searchBean);
        return PageUtil.convert(page);
    }
    /**
     * 查询物流公司
     *
     * @return
     */
    @GetMapping("/channel")
    public ResponseResult channel() {

        return ResponseUtil.success("物流信息",expressService.getVendors());
    }

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public OrderModel detail(Integer id) {
        return orderService.getById(id);
    }

    /**
     * 订单退款
     *
     * @param dto 订单信息 {@link OrderRefundDTO}
     * @return 订单退款操作结果
     */
    @PostMapping("/refund")
    public ResponseResult refund(@RequestBody OrderRefundDTO dto) {
        return orderService.refund(dto);
    }

    /**
     * 发货
     *
     * @param dto 订单信息，{ orderId：xxx, shipSn: xxx, shipChannel: xxx }
     * @return 订单操作结果
     */
    @PostMapping("/ship")
    public ResponseResult ship(@RequestBody OrderShipDTO dto) {
        return orderService.ship(dto);
    }


    /**
     * 回复订单商品
     *
     * @param dto 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("/reply")
    public ResponseResult reply(@RequestBody OrderReplyDTO dto) {
        return orderService.reply(dto);
    }
}

