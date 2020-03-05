package com.hsn.mall.core.request.search;


import com.hsn.mall.core.bean.BaseSearchBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsSearchBean extends BaseSearchBean {
    private Integer goodsId;
    private String goodsSn;
    private String name;
}
