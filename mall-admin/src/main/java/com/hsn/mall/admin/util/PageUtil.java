package com.hsn.mall.admin.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.response.PageResponse;

/**
 * @author huisunan
 * @date 2020/1/14 15:28
 */
public class PageUtil {
    static public PageResponse convert(Page page){
        return new PageResponse(page.getRecords(),page.getTotal(),page.getSize(),page.getCurrent());
    }
}
