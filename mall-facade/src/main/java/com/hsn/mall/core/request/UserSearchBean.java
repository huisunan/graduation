package com.hsn.mall.core.request;

import com.hsn.mall.core.bean.BaseSearchBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huisunan
 * @date 2020/1/20 21:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserSearchBean extends BaseSearchBean {
    private String phone;
}
