package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.GoodsModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.request.create.GoodsAllinone;
import com.hsn.mall.core.request.search.GoodsSearchBean;
import com.hsn.mall.core.response.PageResponse;

/**
 * <p>
 * 商品基本信息表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IGoodsService extends IService<GoodsModel> {
    Page<GoodsModel> page(GoodsSearchBean searchBean);

    Object update(GoodsAllinone goodsAllinone);

    Object create(GoodsAllinone goodsAllinone);

    Object detail(Integer id);
}
