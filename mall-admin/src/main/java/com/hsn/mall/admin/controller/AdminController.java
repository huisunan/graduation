package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.core.service.IAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/adminModel")
public class AdminController {
    @Reference
    private IAdminService iAdminService;

}

