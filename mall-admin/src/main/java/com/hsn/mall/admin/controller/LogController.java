package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.admin.util.PageUtil;
import com.hsn.mall.core.model.LogModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.ILogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @Reference
    private ILogService logService;

    @GetMapping("/list")
    public PageResponse list(){
        Page<LogModel> page = new Page<>(1,10);
        Page<LogModel> res = logService.page(page);
        return PageUtil.convert(res);
    }
}

