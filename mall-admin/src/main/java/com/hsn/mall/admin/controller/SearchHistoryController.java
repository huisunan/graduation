package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.FootprintModel;
import com.hsn.mall.core.model.SearchHistoryModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IFootprintService;
import com.hsn.mall.core.service.ISearchHistoryService;
import com.hsn.mall.core.util.PageUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 搜索历史表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/searchHistory")
public class SearchHistoryController {
    @Reference
    private ISearchHistoryService searchHistoryService;
    @PostMapping("/list")
    public PageResponse<SearchHistoryModel> list(BaseSearchBean searchBean){
        Page<SearchHistoryModel> page = searchHistoryService.page(searchBean);
        return PageUtil.convert(page);
    }
}

