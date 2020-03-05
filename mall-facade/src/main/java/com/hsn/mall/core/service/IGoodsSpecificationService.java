package com.hsn.mall.core.service;

import com.hsn.mall.core.model.GoodsSpecificationModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品规格表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IGoodsSpecificationService extends IService<GoodsSpecificationModel> {

    List<GoodsSpecificationModel> queryByGid(Integer id);
}
