package com.hsn.mall.core.bean;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huisunan
 * @date 2020/1/14 15:27
 */
@Data
public class BaseSearchBean implements Serializable {
    /**
     * 每页显示条数，默认 10
     */
    private long limit = 10;

    /**
     * 当前页
     */
    private long page = 1;

    /**
     * 排序字段信息
     */
    private List<OrderItem> orders = new ArrayList<>();
    /**
     * 关键字,管理员名称
     */
    private String keyword;
}
