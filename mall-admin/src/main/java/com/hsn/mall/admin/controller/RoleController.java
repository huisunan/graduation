package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.hsn.mall.admin.annotation.Permission;
import com.hsn.mall.admin.bean.ResponseResult;
import com.hsn.mall.admin.vo.RoleAllVO;
import com.hsn.mall.core.model.RoleModel;
import com.hsn.mall.core.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/role")
@Permission("角色管理")
public class RoleController {
    @Reference
    private IRoleService roleService;

    @GetMapping("/all")
    @Permission("列表")
    public List<RoleAllVO> all(){
        List<RoleModel> list = roleService.list();
        return list.stream()
                .map(i -> new RoleAllVO().setLabel(i.getName()).setValue(i.getId()))
                .collect(Collectors.toList());
    }
    @Autowired
    ApplicationContext context;

    @GetMapping("/allPermission")
    @Permission("获取所有权限")
    public List<JSONObject> allPermission(){
        Map<String, Object> beans = context.getBeansWithAnnotation(Controller.class);
        String basePackage = "com.hsn.mall";
        List<JSONObject> res = new ArrayList<>();
        for (Map.Entry<String,Object> bean : beans.entrySet()){
            Object entry = bean.getValue();
            if (!entry.getClass().getName().contains(basePackage)){
                continue;
            }
            Class<?> clazz = entry.getClass().getSuperclass();
            //类上的注解
            RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
            Permission permission = clazz.getAnnotation(Permission.class);
            String rootUrl;
            if (mapping == null){
                rootUrl = "";
            }else {
                String t = mapping.value()[0];
                rootUrl = t.startsWith("/") ? t : "/" + t;
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("desc",permission == null ? "":permission.value());
            List<JSONObject> list = new ArrayList<>();
            for (Method method : clazz.getMethods()) {
                RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
                Permission methodPermission = method.getAnnotation(Permission.class);
                JSONObject subObject = new JSONObject();
                if (methodMapping == null){
                    continue;
                }else {
                    String value = methodMapping.value()[0];
                    String subUrl = value.startsWith("/") ? rootUrl + value : rootUrl + "/" + value;
                    subObject.put("url",subUrl);
                }
                if (methodPermission != null){
                    String value = methodPermission.value();
                    subObject.put("desc",value);
                }
                list.add(subObject);
            }
            jsonObject.put("list",list);
            res.add(jsonObject);
        }
        System.out.println(res);
        return res;
    }
}

