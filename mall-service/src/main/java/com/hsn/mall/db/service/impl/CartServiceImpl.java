package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.CartModel;
import com.hsn.mall.db.mapper.CartMapper;
import com.hsn.mall.core.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 购物车商品表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ICartService.class)
public class CartServiceImpl extends ServiceImpl<CartMapper, CartModel> implements ICartService {

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateProduct(Integer id, String goodsSn, String name, BigDecimal price, String url) {
        CartModel cart = new CartModel();
        cart.setId(id);
        cart.setPrice(price);
        cart.setPicUrl(url);
        cart.setGoodsSn(goodsSn);
        cart.setGoodsName(name);
        this.updateById(cart);
    }
}
