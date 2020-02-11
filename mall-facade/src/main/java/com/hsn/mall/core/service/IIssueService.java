package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.IssueModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 常见问题表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IIssueService extends IService<IssueModel> {
    Page<IssueModel> page(BaseSearchBean searchBean);
}
