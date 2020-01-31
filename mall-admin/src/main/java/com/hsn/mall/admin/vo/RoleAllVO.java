package com.hsn.mall.admin.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author huisunan
 * @date 2020/1/18 22:43
 */
@Data
@Accessors(chain = true)
public class RoleAllVO {
    private Integer value;
    private String label;
}
