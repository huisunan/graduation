package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.SearchHistoryModel;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.SearchHistoryMapper;
import com.hsn.mall.core.service.ISearchHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 搜索历史表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ISearchHistoryService.class)
public class SearchHistoryServiceImpl extends ServiceImpl<SearchHistoryMapper, SearchHistoryModel> implements ISearchHistoryService {

    @Override
    public Page<SearchHistoryModel> page(BaseSearchBean searchBean) {
        QueryWrapper<SearchHistoryModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getKeyword())){
            query.like("keyword",searchBean.getKeyword());
        }
        return page(PageUtil.create(searchBean),query);
    }
}
