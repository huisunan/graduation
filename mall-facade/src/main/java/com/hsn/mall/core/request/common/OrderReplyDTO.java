package com.hsn.mall.core.request.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderReplyDTO implements Serializable {
    private Integer commentId;
    private String content;
}
