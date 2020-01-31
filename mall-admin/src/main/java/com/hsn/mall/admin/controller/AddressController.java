package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.admin.bean.ResponseResult;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.AddressModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IAddressService;
import com.hsn.mall.core.service.IAdminService;
import com.hsn.mall.core.util.PageUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Reference
    private IAddressService addressService;
    @PostMapping("/list")
    public PageResponse<AddressModel> list(BaseSearchBean baseSearchBean){
        Page<AddressModel> res = addressService.page(baseSearchBean);
        return PageUtil.convert(res);
    }
}

