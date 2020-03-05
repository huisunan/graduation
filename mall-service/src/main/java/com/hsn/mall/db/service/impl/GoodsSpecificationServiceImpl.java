package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsn.mall.core.model.GoodsProductModel;
import com.hsn.mall.core.model.GoodsSpecificationModel;
import com.hsn.mall.db.mapper.GoodsSpecificationMapper;
import com.hsn.mall.core.service.IGoodsSpecificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 商品规格表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IGoodsSpecificationService.class)
public class GoodsSpecificationServiceImpl extends ServiceImpl<GoodsSpecificationMapper, GoodsSpecificationModel> implements IGoodsSpecificationService {

    @Override
    public List<GoodsSpecificationModel> queryByGid(Integer id) {
        QueryWrapper<GoodsSpecificationModel> query = new QueryWrapper<>();
        query.eq("goods_id",id);
        return list(query);
    }
}
