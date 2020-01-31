package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.model.LogModel;
import com.hsn.mall.core.request.search.LogSearchDTO;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.ILogService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/list")
    public PageResponse<LogModel> list(@RequestBody LogSearchDTO search){
        Page<LogModel> res = logService.page(search);
        return PageUtil.convert(res);
    }
}

