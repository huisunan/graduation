package com.hsn.mall.core.enumeration;

import lombok.Data;

public enum OrderStatusEnum {
    STATUS_CREATE(101,"未付款"),
    STATUS_PAY(201,"已付款"),
    STATUS_SHIP(301,"已发货"),
    STATUS_CONFIRM(401,"已收货"),
    STATUS_CANCEL(102,"已取消"),
    STATUS_AUTO_CANCEL(103,"已取消(系统)"),
    STATUS_ADMIN_CANCEL(104,"已取消(管理员)"),
    STATUS_REFUND(202,"订单取消，退款中"),
    STATUS_REFUND_CONFIRM(203,"已退款"),
    STATUS_AUTO_CONFIRM(402,"已收货(系统)"),
    STATUS_PAY_GROUPON(200,"已付款团购"),
    STATUS_TIMEOUT_GROUPON(204,"已超时团购");
    OrderStatusEnum(Integer value,String des){
        this.value = value;
        this.des = des;
    }
    public Integer value;
    public String des;

}
