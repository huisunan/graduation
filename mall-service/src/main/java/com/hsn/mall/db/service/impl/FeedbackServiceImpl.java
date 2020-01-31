package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.FeedbackModel;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.FeedbackMapper;
import com.hsn.mall.core.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public Page<FeedbackModel> page(BaseSearchBean searchBean) {
        QueryWrapper<FeedbackModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getKeyword())){
            query.like("username",searchBean.getKeyword());
        }
        return page(PageUtil.create(searchBean),query);
    }
}
