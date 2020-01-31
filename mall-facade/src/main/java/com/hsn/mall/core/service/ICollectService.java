package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.CollectModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface ICollectService extends IService<CollectModel> {
    Page<CollectModel> page(BaseSearchBean searchBean);
}
