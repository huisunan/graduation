package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.AdvertisementModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.request.search.AdvertisementSearchBean;

/**
 * <p>
 * 广告表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IAdvertisementService extends IService<AdvertisementModel> {
    Page<AdvertisementModel> page(AdvertisementSearchBean searchBean);
}
