package com.hsn.mall.core.service;

import com.hsn.mall.core.model.GoodsAttributeModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品参数表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IGoodsAttributeService extends IService<GoodsAttributeModel> {

    List<GoodsAttributeModel> queryByGid(Integer id);
}
