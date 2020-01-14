package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.BrandModel;
import com.hsn.mall.db.mapper.BrandMapper;
import com.hsn.mall.core.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 品牌商表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IBrandService.class)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, BrandModel> implements IBrandService {

}
