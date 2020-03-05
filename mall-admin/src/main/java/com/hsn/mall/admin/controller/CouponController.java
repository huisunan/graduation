package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.core.constants.CouponConstant;
import com.hsn.mall.core.model.CouponModel;
import com.hsn.mall.core.model.CouponUserModel;
import com.hsn.mall.core.request.search.CouponSearchBean;
import com.hsn.mall.core.request.search.CouponUserSearchBean;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.ICouponService;
import com.hsn.mall.core.service.ICouponUserService;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 优惠券信息及规则表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Reference
    private ICouponService couponService;
    @Reference
    private ICouponUserService couponUserService;


    @PostMapping("/list")
    public PageResponse<CouponModel> list(@RequestBody CouponSearchBean searchBean) {
        return PageUtil.convert(couponService.page(searchBean));
    }

    @GetMapping("/listuser")
    public PageResponse<CouponUserModel> listuser(@RequestBody CouponUserSearchBean searchBean) {
        return PageUtil.convert(couponUserService.page(searchBean));
    }

    private Object validate(CouponModel coupon) {
        String name = coupon.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.fail();
        }
        return null;
    }

    @PostMapping("/create")
    public Object create(@RequestBody CouponModel coupon) {
        Object error = validate(coupon);
        if (error != null) {
            return error;
        }

        // 如果是兑换码类型，则这里需要生存一个兑换码
        if (coupon.getType().equals(CouponConstant.TYPE_CODE)) {
            String code = couponService.generateCode();
            coupon.setCode(code);
        }

        couponService.save(coupon);
        return ResponseUtil.add();
    }

    public CouponModel read(@NotNull Integer id) {
        return couponService.getById(id);
    }

    @PostMapping("/update")
    public Object update(@RequestBody CouponModel coupon) {
        Object error = validate(coupon);
        if (error != null) {
            return error;
        }
        if (!couponService.updateById(coupon) ) {
            return ResponseUtil.fail();
        }
        return ResponseUtil.update();
    }


    @PostMapping("/delete")
    public Object delete(@RequestBody Integer id) {
        couponService.removeById(id);
        return ResponseUtil.delete();
    }
}

