package com.hsn.mall.admin.controller;


import com.hsn.mall.core.model.AddressModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/addressModel")
public class AddressController {
    @GetMapping("/test")
    public Map<String,String> test(){
        Map<String,String> map = new HashMap<>();
        map.put("ddd","你好啊");
        return map;
    }
}

