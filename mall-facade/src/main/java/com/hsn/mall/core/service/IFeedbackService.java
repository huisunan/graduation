package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.FeedbackModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 意见反馈表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IFeedbackService extends IService<FeedbackModel> {
    Page<FeedbackModel> page(BaseSearchBean searchBean);
}
