package com.hsn.mall.db.mapper;

import com.hsn.mall.core.model.RegionModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

/**
 * <p>
 * 行政区域表 Mapper 接口
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface RegionMapper extends BaseMapper<RegionModel> {
    @MapKey("id")
    Map<Integer,RegionModel> selectAll();
}
