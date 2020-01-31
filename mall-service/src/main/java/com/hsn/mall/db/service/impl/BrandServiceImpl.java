package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.BrandModel;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.BrandMapper;
import com.hsn.mall.core.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 品牌商表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IBrandService.class)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, BrandModel> implements IBrandService {

    @Override
    public Page<BrandModel> page(BaseSearchBean searchBean) {
        QueryWrapper<BrandModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getKeyword())){
            query.like("id",searchBean.getKeyword());
            query.or().like("name",searchBean.getKeyword());

        }
        return page(PageUtil.create(searchBean),query);
    }
}
