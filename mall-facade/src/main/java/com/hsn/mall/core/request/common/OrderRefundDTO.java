package com.hsn.mall.core.request.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderRefundDTO implements Serializable {
    private Integer orderId;
    private String refundMoney;
}
