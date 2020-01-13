package com.hsn.mall.db.service.impl;

import com.hsn.mall.core.model.CartModel;
import com.hsn.mall.db.mapper.CartMapper;
import com.hsn.mall.core.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车商品表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, CartModel> implements ICartService {

}
