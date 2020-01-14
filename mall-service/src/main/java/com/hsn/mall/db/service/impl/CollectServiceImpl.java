package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.CollectModel;
import com.hsn.mall.db.mapper.CollectMapper;
import com.hsn.mall.core.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ICollectService.class)
public class CollectServiceImpl extends ServiceImpl<CollectMapper, CollectModel> implements ICollectService {

}
