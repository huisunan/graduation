package com.hsn.mall.admin.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
@RestController
@RequestMapping("/addressModel")
public class AddressController {
    @GetMapping("/test")
    public String test() throws InterruptedException {
        return "你好啊";
    }
}

