package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.GoodsSpecificationModel;
import com.hsn.mall.db.mapper.GoodsSpecificationMapper;
import com.hsn.mall.core.service.IGoodsSpecificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

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

}
