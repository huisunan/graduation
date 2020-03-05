package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.CouponUserModel;
import com.hsn.mall.core.request.search.CouponUserSearchBean;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.CouponUserMapper;
import com.hsn.mall.core.service.ICouponUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 优惠券用户使用表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ICouponUserService.class)
public class CouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUserModel> implements ICouponUserService {

    @Override
    public Page<CouponUserModel> page(CouponUserSearchBean searchBean) {
        QueryWrapper<CouponUserModel> query = new QueryWrapper<>();
        if (searchBean.getCouponId() != null){
            query.eq("coupon_id",searchBean.getCouponId());
        }
        if (searchBean.getUserId() != null){
            query.eq("user_id",searchBean.getUserId());
        }
        if (searchBean.getStatus() != null){
            query.eq("status",searchBean.getStatus());
        }
        return page(PageUtil.create(searchBean),query);
    }
}
