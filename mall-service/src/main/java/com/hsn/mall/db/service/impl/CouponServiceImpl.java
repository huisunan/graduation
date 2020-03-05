package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.constants.CouponConstant;
import com.hsn.mall.core.model.CouponModel;
import com.hsn.mall.core.request.search.CouponSearchBean;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.CouponMapper;
import com.hsn.mall.core.service.ICouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * <p>
 * 优惠券信息及规则表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ICouponService.class)
public class CouponServiceImpl extends ServiceImpl<CouponMapper, CouponModel> implements ICouponService {

    @Override
    public Page<CouponModel> page(CouponSearchBean searchBean) {
        QueryWrapper<CouponModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getName())){
            query.like("name",searchBean.getName());
        }
        if (searchBean.getStatus() != null)
        {
            query.eq("status",searchBean.getStatus());
        }
        if (searchBean.getType() != null){
            query.eq("type",searchBean.getType());
        }
        return page(PageUtil.create(searchBean),query);
    }

    @Override
    public String generateCode() {
        String code = getRandomNum(8);
        while(findByCode(code) != null){
            code = getRandomNum(8);
        }
        return code;
    }
    public CouponModel findByCode(String code) {
        QueryWrapper<CouponModel> query = new QueryWrapper<>();
        query.eq("code",code);
        query.eq("type",CouponConstant.TYPE_CODE);
        query.eq("status",CouponConstant.STATUS_NORMAL);
        List<CouponModel> couponList = this.list(query);
        if(couponList.size() > 1){
            throw new RuntimeException("");
        }
        else if(couponList.size() == 0){
            return null;
        }
        else {
            return couponList.get(0);
        }
    }

    private String getRandomNum(Integer num) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        base += "0123456789";

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
