package com.hsn.mall.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author huisunan
 * @date 2020/1/14 15:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 查询数据列表
     */
    private List<T> records;
    /**
     * 总数
     */
    private Long total;
    /**
     * 每页显示条数，默认 10
     */
    private Long size;

    /**
     * 当前页
     */
    private Long current;
}
