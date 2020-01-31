package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.CollectModel;
import com.hsn.mall.core.model.FootprintModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.ICollectService;
import com.hsn.mall.core.service.IFootprintService;
import com.hsn.mall.core.util.PageUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户浏览足迹表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/footprint")
public class FootprintController {
    @Reference
    private IFootprintService footprintService;
    @PostMapping("/list")
    public PageResponse<FootprintModel> list(BaseSearchBean searchBean){
        Page<FootprintModel> page = footprintService.page(searchBean);
        return PageUtil.convert(page);
    }
}

