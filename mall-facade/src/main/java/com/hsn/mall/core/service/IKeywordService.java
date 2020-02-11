package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.KeywordModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.request.search.KeywordSearchDTO;

/**
 * <p>
 * 关键字表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IKeywordService extends IService<KeywordModel> {
    Page<KeywordModel> page(KeywordSearchDTO dto);
}
