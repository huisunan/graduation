package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.SearchHistoryModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 搜索历史表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface ISearchHistoryService extends IService<SearchHistoryModel> {
    Page<SearchHistoryModel> page(BaseSearchBean searchBean);
}
