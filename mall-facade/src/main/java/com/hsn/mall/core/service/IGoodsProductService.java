package com.hsn.mall.core.service;

import com.hsn.mall.core.model.GoodsProductModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品货品表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IGoodsProductService extends IService<GoodsProductModel> {

    int addStock(Integer productId, Integer number);

    List<GoodsProductModel> queryByGid(Integer id);
}
