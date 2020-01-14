package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.GoodsModel;
import com.hsn.mall.db.mapper.GoodsMapper;
import com.hsn.mall.core.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 商品基本信息表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IGoodsService.class)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsModel> implements IGoodsService {

}
