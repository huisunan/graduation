package com.hsn.mall.core.request.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderShipDTO implements Serializable {
    private Integer orderId;
    private String shipSn;
    private String shipChannel;
}
