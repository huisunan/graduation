package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.RegionModel;
import com.hsn.mall.db.mapper.RegionMapper;
import com.hsn.mall.core.service.IRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 行政区域表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IRegionService.class)
public class RegionServiceImpl extends ServiceImpl<RegionMapper, RegionModel> implements IRegionService {

    @Override
    public Map<Integer, RegionModel> all() {
        return this.baseMapper.selectAll();
    }
}
