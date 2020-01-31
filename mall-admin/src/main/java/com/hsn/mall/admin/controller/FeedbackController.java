package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.FeedbackModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IFeedbackService;
import com.hsn.mall.core.util.PageUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Reference
    private IFeedbackService feedbackService;

    @PostMapping("/list")
    public PageResponse<FeedbackModel> list(BaseSearchBean searchBean){
        Page<FeedbackModel> page = feedbackService.page(searchBean);
        return PageUtil.convert(page);
    }
}

