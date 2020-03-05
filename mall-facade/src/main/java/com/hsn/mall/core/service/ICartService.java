package com.hsn.mall.core.service;

import com.hsn.mall.core.model.CartModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 购物车商品表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface ICartService extends IService<CartModel> {

    void updateProduct(Integer id, String goodsSn, String name, BigDecimal price, String url);
}
