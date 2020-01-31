package com.hsn.mall.core.request.search;

import com.hsn.mall.core.bean.BaseSearchBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author huisunan
 * @date 2020/1/29 15:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderSearchBean extends BaseSearchBean {
    private Integer userId;
    private String orderSn;
    private List<Integer> orderStatusArray;
}
