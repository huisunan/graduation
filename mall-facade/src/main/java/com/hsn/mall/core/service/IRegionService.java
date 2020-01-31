package com.hsn.mall.core.service;

import com.hsn.mall.core.model.RegionModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 行政区域表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IRegionService extends IService<RegionModel> {
    Map<Integer,RegionModel> all();
}
