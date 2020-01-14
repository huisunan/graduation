package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.GrouponModel;
import com.hsn.mall.db.mapper.GrouponMapper;
import com.hsn.mall.core.service.IGrouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 团购活动表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IGrouponService.class)
public class GrouponServiceImpl extends ServiceImpl<GrouponMapper, GrouponModel> implements IGrouponService {

}
