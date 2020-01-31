package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.FootprintModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户浏览足迹表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IFootprintService extends IService<FootprintModel> {
    Page<FootprintModel> page(BaseSearchBean searchBean);
}
