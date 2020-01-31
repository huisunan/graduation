package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.LogModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.request.search.LogSearchDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface ILogService extends IService<LogModel> {
    Page<LogModel> page(LogSearchDTO dto);
}
