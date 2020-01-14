package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.FootprintModel;
import com.hsn.mall.db.mapper.FootprintMapper;
import com.hsn.mall.core.service.IFootprintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户浏览足迹表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IFootprintService.class)
public class FootprintServiceImpl extends ServiceImpl<FootprintMapper, FootprintModel> implements IFootprintService {

}
