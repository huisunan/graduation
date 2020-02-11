package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.KeywordModel;
import com.hsn.mall.core.request.search.KeywordSearchDTO;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.KeywordMapper;
import com.hsn.mall.core.service.IKeywordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 关键字表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IKeywordService.class)
public class KeywordServiceImpl extends ServiceImpl<KeywordMapper, KeywordModel> implements IKeywordService {

    @Override
    public Page<KeywordModel> page(KeywordSearchDTO dto) {
        QueryWrapper<KeywordModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getKeyword())){
            query.like("keyword",dto.getKeyword());
        }
        if (StringUtils.isNotBlank(dto.getUrl())){
            query.like("url",dto.getUrl());
        }

        return page(PageUtil.create(dto),query);
    }
}
