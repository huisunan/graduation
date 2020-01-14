package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.TopicModel;
import com.hsn.mall.db.mapper.TopicMapper;
import com.hsn.mall.core.service.ITopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ITopicService.class)
public class TopicServiceImpl extends ServiceImpl<TopicMapper, TopicModel> implements ITopicService {

}
