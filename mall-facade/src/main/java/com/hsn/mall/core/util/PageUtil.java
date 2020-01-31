package com.hsn.mall.core.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.response.PageResponse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huisunan
 * @date 2020/1/14 15:28
 */
public class PageUtil {
    public static <T> PageResponse<T> convert(Page<T> page){
        return new PageResponse<>(page.getRecords(),page.getTotal(),page.getSize(),page.getCurrent());
    }


    public static <T,C> PageResponse<C> convert(Page<T> page, Class<C> clazz)  {
        List<C> list = new ArrayList<>();
        PageResponse<C> response = new PageResponse<C>();
        response.setCurrent(page.getCurrent());
        response.setSize(page.getSize());
        response.setTotal(page.getTotal());
        response.setCurrent(page.getCurrent());
        for (T record : page.getRecords()) {
            try {
                C vo = clazz.newInstance();
                BeanUtils.copyProperties(record,vo);
                list.add(vo);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        response.setRecords(list);
        return response;
    }
    public static <T> Page<T> create(BaseSearchBean baseSearchBean){
        return new Page<>(baseSearchBean.getPage(),baseSearchBean.getLimit());
    }
}
