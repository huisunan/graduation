package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.FeedbackModel;
import com.hsn.mall.db.mapper.FeedbackMapper;
import com.hsn.mall.core.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 意见反馈表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IFeedbackService.class)
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, FeedbackModel> implements IFeedbackService {

}
