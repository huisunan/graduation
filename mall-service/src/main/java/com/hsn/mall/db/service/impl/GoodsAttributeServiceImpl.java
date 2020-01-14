package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.GoodsAttributeModel;
import com.hsn.mall.db.mapper.GoodsAttributeMapper;
import com.hsn.mall.core.service.IGoodsAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 商品参数表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IGoodsAttributeService.class)
public class GoodsAttributeServiceImpl extends ServiceImpl<GoodsAttributeMapper, GoodsAttributeModel> implements IGoodsAttributeService {

}
