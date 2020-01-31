package com.hsn.mall.db.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.AddressModel;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.AddressMapper;
import com.hsn.mall.core.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IAddressService.class)
public class AddressServiceImpl extends ServiceImpl<AddressMapper, AddressModel> implements IAddressService {

    @Override
    public Page<AddressModel> page(BaseSearchBean searchBean) {
        QueryWrapper<AddressModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getKeyword())){
            query.like("name",searchBean.getKeyword());
        }
        Page<AddressModel> page = PageUtil.create(searchBean);
        return page(page,query);
    }
}
