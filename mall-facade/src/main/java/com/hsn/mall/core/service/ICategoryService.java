package com.hsn.mall.core.service;

import com.hsn.mall.core.model.CategoryModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface ICategoryService extends IService<CategoryModel> {
    Map<Integer,CategoryModel> all();

    List<CategoryModel> queryL1();

}
