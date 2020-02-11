package com.hsn.mall.db.mapper;

import com.hsn.mall.core.model.GoodsProductModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品货品表 Mapper 接口
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface GoodsProductMapper extends BaseMapper<GoodsProductModel> {

    int addStock(@Param("id") Integer id, @Param("num") Integer num);
}
