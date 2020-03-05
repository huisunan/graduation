package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsn.mall.core.model.GoodsProductModel;
import com.hsn.mall.db.mapper.GoodsProductMapper;
import com.hsn.mall.core.service.IGoodsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 商品货品表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IGoodsProductService.class)
public class GoodsProductServiceImpl extends ServiceImpl<GoodsProductMapper, GoodsProductModel> implements IGoodsProductService {

    @Override
    public int addStock(Integer productId, Integer number) {
        return this.baseMapper.addStock(productId,number);
    }

    @Override
    public List<GoodsProductModel> queryByGid(Integer id) {
        QueryWrapper<GoodsProductModel> query = new QueryWrapper<>();
        query.eq("goods_id",id);
        return list(query);
    }
}
