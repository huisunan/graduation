package com.hsn.mall.core.request.search;

import com.hsn.mall.core.bean.BaseSearchBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentSearchBean extends BaseSearchBean {
    private String userId;
    private String valueId;
}
