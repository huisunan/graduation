package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.AdvertisementModel;
import com.hsn.mall.db.mapper.AdvertisementMapper;
import com.hsn.mall.core.service.IAdvertisementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 广告表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IAdvertisementService.class)
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, AdvertisementModel> implements IAdvertisementService {

}
