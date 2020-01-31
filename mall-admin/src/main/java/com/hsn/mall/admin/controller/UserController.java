package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.admin.annotation.Permission;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.model.UserModel;
import com.hsn.mall.core.request.UserSearchBean;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/user")
@Permission("用户管理")
public class UserController {
    @Reference
    IUserService userService;

    @PostMapping("/list")
    @Permission("列表查询")
    public PageResponse<UserModel> list(@RequestBody UserSearchBean dto){
        Page<UserModel> page = userService.page(dto);
        return PageUtil.convert(page);
    }
}

