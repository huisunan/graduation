package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.GoodsProductModel;
import com.hsn.mall.db.mapper.GoodsProductMapper;
import com.hsn.mall.core.service.IGoodsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

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

}
