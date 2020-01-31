package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.AddressModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收货地址表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IAddressService extends IService<AddressModel> {
    Page<AddressModel> page(BaseSearchBean searchBean);
}
