package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.ResponseResult;
import com.hsn.mall.core.enumeration.OrderStatusEnum;
import com.hsn.mall.core.exception.BaseException;
import com.hsn.mall.core.model.CommentModel;
import com.hsn.mall.core.model.OrderGoodsModel;
import com.hsn.mall.core.model.OrderModel;
import com.hsn.mall.core.request.common.OrderRefundDTO;
import com.hsn.mall.core.request.common.OrderReplyDTO;
import com.hsn.mall.core.request.common.OrderShipDTO;
import com.hsn.mall.core.request.search.OrderSearchBean;
import com.hsn.mall.core.service.ICommentService;
import com.hsn.mall.core.service.IGoodsProductService;
import com.hsn.mall.core.service.IOrderGoodsService;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.util.ResponseUtil;
import com.hsn.mall.db.mapper.OrderMapper;
import com.hsn.mall.core.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Reference
    private IOrderGoodsService orderGoodsService;

    @Reference
    private IGoodsProductService productService;

    @Reference
    private ICommentService commentService;

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

    /**
     * 订单退款
     * <p>
     * 1. 检测当前订单是否能够退款;
     * 2. 微信退款操作;
     * 3. 设置订单退款确认状态；
     * 4. 订单商品库存回库。
     * <p>
     * TODO
     * 虽然接入了微信退款API，但是从安全角度考虑，建议开发者删除这里微信退款代码，采用以下两步走步骤：
     * 1. 管理员登录微信官方支付平台点击退款操作进行退款
     * 2. 管理员登录litemall管理后台点击退款操作进行订单状态修改和商品库存回库
     *
     * @param dto 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @Override
    public ResponseResult refund(OrderRefundDTO dto) {
        ResponseResult result = ResponseUtil.success("退款成功");

        Integer orderId = dto.getOrderId();
        String refundMoney = dto.getRefundMoney();
        if (orderId == null) {
            return result.fail("订单ID不能为空");
        }
        if (StringUtils.isEmpty(refundMoney)) {
            return result.fail("退款金额不能为空");
        }
        OrderModel order = getById(orderId);
        if (order == null) {
            return result.fail("");
        }

        if (order.getActualPrice().compareTo(new BigDecimal(refundMoney)) != 0) {
            return result.fail("");
        }

        // 如果订单不是退款状态，则不能退款
        if (!order.getOrderStatus().equals(OrderStatusEnum.STATUS_REFUND.value)) {
            return result.fail("订单不能确认收货");
        }

//        // 微信退款
//        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
//        wxPayRefundRequest.setOutTradeNo(order.getOrderSn());
//        wxPayRefundRequest.setOutRefundNo("refund_" + order.getOrderSn());
//        // 元转成分
//        Integer totalFee = order.getActualPrice().multiply(new BigDecimal(100)).intValue();
//        wxPayRefundRequest.setTotalFee(totalFee);
//        wxPayRefundRequest.setRefundFee(totalFee);
//
//        WxPayRefundResult wxPayRefundResult;
//        try {
//            wxPayRefundResult = wxPayService.refund(wxPayRefundRequest);
//        } catch (WxPayException e) {
//            logger.error(e.getMessage(), e);
//            return ResponseUtil.fail(ORDER_REFUND_FAILED, "订单退款失败");
//        }
//        if (!wxPayRefundResult.getReturnCode().equals("SUCCESS")) {
//            logger.warn("refund fail: " + wxPayRefundResult.getReturnMsg());
//            return ResponseUtil.fail(ORDER_REFUND_FAILED, "订单退款失败");
//        }
//        if (!wxPayRefundResult.getResultCode().equals("SUCCESS")) {
//            logger.warn("refund fail: " + wxPayRefundResult.getReturnMsg());
//            return ResponseUtil.fail(ORDER_REFUND_FAILED, "订单退款失败");
//        }

        LocalDateTime now = LocalDateTime.now();
        // 设置订单取消状态
        order.setOrderStatus(OrderStatusEnum.STATUS_REFUND_CONFIRM.value);
        order.setEndTime(now);
        // 记录订单退款相关信息
        order.setRefundAmount(order.getActualPrice());
        order.setRefundType("微信退款接口");
//        order.setRefundContent(wxPayRefundResult.getRefundId());
        order.setRefundTime(now);
        if (!updateById(order)) {
            throw new RuntimeException("更新数据已失效");
        }

        // 商品货品数量增加
        Map<String,Object> map = new HashMap<>();
        map.put("order_id",orderId);
        List<OrderGoodsModel> orderGoodsList = orderGoodsService.listByMap(map);
        for (OrderGoodsModel orderGoods : orderGoodsList) {
            Integer productId = orderGoods.getProductId();
            Integer number = orderGoods.getNumber();
            if (productService.addStock(productId, number) == 0) {
                throw new RuntimeException("商品货品库存增加失败");
            }
        }

//        //TODO 发送邮件和短信通知，这里采用异步发送
//        // 退款成功通知用户, 例如“您申请的订单退款 [ 单号:{1} ] 已成功，请耐心等待到账。”
//        // 注意订单号只发后6位
//        notifyService.notifySmsTemplate(order.getMobile(), NotifyType.REFUND,
//                new String[]{order.getOrderSn().substring(8, 14)});

//        logHelper.logOrderSucceed("退款", "订单编号 " + orderId);
        return result;
    }

    @Override
    public ResponseResult ship(OrderShipDTO dto) {
        Integer orderId = dto.getOrderId();
        String shipSn = dto.getShipSn();
        String shipChannel = dto.getShipChannel();
        if (orderId == null || shipSn == null || shipChannel == null) {
            return ResponseUtil.fail("参数异常");
        }

        OrderModel order = getById(orderId);
        if (order == null) {
            return ResponseUtil.fail("参数异常");
        }

        // 如果订单不是已付款状态，则不能发货
        if (!order.getOrderStatus().equals(OrderStatusEnum.STATUS_PAY.value)) {
            return ResponseUtil.fail("订单不能确认收货");
        }

        order.setOrderStatus(OrderStatusEnum.STATUS_SHIP.value);
        order.setShipSn(shipSn);
        order.setShipChannel(shipChannel);
        order.setShipTime(LocalDateTime.now());
        if (!updateById(order)) {
            return ResponseUtil.fail("更新超时");
        }

//        //TODO 发送邮件和短信通知，这里采用异步发送
//        // 发货会发送通知短信给用户:          *
//        // "您的订单已经发货，快递公司 {1}，快递单 {2} ，请注意查收"
//        notifyService.notifySmsTemplate(order.getMobile(), NotifyType.SHIP, new String[]{shipChannel, shipSn});
//
//        logHelper.logOrderSucceed("发货", "订单编号 " + orderId);
        return ResponseUtil.success("发货成功!");
    }

    @Override
    public ResponseResult reply(OrderReplyDTO dto) {
        Integer commentId = dto.getCommentId();
        if (commentId == null || commentId == 0) {
            return ResponseUtil.fail("id不能为空");
        }
        // 目前只支持回复一次
        if (commentService.getById(commentId) != null) {
            return ResponseUtil.fail("订单商品已回复！");
        }
        String content = dto.getContent();
        if (org.springframework.util.StringUtils.isEmpty(content)) {
            return ResponseUtil.fail();
        }
        // 创建评价回复
        CommentModel comment = new CommentModel();
        comment.setType(2);
        comment.setValueId(commentId);
        comment.setContent(content);
        comment.setUserId(0);                 // 评价回复没有用
        comment.setStar(0);           // 评价回复没有用
        comment.setHasPicture(false);        // 评价回复没有用
        commentService.save(comment);

        return ResponseUtil.success("回复成功");
    }
}
