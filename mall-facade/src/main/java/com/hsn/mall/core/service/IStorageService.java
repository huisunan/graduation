package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.StorageModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文件存储表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface IStorageService extends IService<StorageModel> {
    Page<StorageModel> page(BaseSearchBean dto);
}
