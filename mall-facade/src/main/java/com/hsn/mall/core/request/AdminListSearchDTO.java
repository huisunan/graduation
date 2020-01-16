package com.hsn.mall.core.request;

import com.hsn.mall.core.bean.BaseSearchBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员用户查询DTO
 * @author huisunan
 * @date 2020/1/16 11:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminListSearchDTO extends BaseSearchBean {
    /**
     * 关键字,管理员名称
     */
    private String keyWord;
}
