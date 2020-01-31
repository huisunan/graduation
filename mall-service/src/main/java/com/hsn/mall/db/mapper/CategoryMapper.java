package com.hsn.mall.db.mapper;

import com.hsn.mall.core.model.CategoryModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

/**
 * <p>
 * 类目表 Mapper 接口
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface CategoryMapper extends BaseMapper<CategoryModel> {
    @MapKey("id")
    Map<Integer,CategoryModel> selectAll();
}
