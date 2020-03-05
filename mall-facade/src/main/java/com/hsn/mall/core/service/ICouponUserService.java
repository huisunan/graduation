package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.CouponUserModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.request.search.CouponUserSearchBean;

/**
 * <p>
 * 优惠券用户使用表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface ICouponUserService extends IService<CouponUserModel> {
    Page<CouponUserModel> page(CouponUserSearchBean searchBean);
}
