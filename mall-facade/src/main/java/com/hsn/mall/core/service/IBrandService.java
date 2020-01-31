package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.BrandModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌商表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IBrandService extends IService<BrandModel> {
    Page<BrandModel> page(BaseSearchBean searchBean);
}
