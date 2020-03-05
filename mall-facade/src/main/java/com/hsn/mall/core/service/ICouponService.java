package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.CouponModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.request.search.CouponSearchBean;

/**
 * <p>
 * 优惠券信息及规则表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface ICouponService extends IService<CouponModel> {
    Page<CouponModel> page(CouponSearchBean searchBean);

    String generateCode();

}
