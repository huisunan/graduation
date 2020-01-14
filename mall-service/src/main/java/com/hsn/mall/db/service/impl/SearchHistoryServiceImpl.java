package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.SearchHistoryModel;
import com.hsn.mall.db.mapper.SearchHistoryMapper;
import com.hsn.mall.core.service.ISearchHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
