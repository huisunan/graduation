package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.KeywordModel;
import com.hsn.mall.db.mapper.KeywordMapper;
import com.hsn.mall.core.service.IKeywordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
