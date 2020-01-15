package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.admin.annotation.LogExclude;
import com.hsn.mall.admin.bean.ResponseResult;
import com.hsn.mall.admin.util.ResponseUtil;
import com.hsn.mall.core.model.AdminModel;
import com.hsn.mall.core.request.AdminCreateDTO;
import com.hsn.mall.core.service.IAdminService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Reference
    private IAdminService iAdminService;

    /**
     * 添加用户
     * @param dto
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody AdminCreateDTO dto){
        AdminModel byUserName = iAdminService.getByUserName(dto.getUsername());
        if (byUserName != null)
            return ResponseUtil.fail("用户名已存在!");
        AdminModel model = new AdminModel();
        model.setLastLoginTime(LocalDateTime.MIN);
        BeanUtils.copyProperties(dto,model);
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(StringUtils.reverse(dto.getUsername()));
        //密码
        Object source = dto.getPassword();
        //加密次数
        int hashIterations = 1024;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        model.setPassword(result.toString());
        iAdminService.save(model);
        return ResponseUtil.success("添加成功!");
    }

}

