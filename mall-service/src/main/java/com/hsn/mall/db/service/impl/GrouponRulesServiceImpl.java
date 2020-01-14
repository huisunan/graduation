package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.GrouponRulesModel;
import com.hsn.mall.db.mapper.GrouponRulesMapper;
import com.hsn.mall.core.service.IGrouponRulesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 团购规则表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IGrouponRulesService.class)
public class GrouponRulesServiceImpl extends ServiceImpl<GrouponRulesMapper, GrouponRulesModel> implements IGrouponRulesService {

}
