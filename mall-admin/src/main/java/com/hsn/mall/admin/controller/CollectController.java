package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.CollectModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.ICollectService;
import com.hsn.mall.core.util.PageUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Reference
    private ICollectService collectService;
    @PostMapping("/list")
    public PageResponse<CollectModel> list(BaseSearchBean searchBean){
        Page<CollectModel> page = collectService.page(searchBean);
        return PageUtil.convert(page);
    }
}

