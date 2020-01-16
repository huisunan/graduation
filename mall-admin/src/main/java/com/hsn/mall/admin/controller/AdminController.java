package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.admin.annotation.Permission;
import com.hsn.mall.admin.bean.ResponseResult;
import com.hsn.mall.admin.util.PageUtil;
import com.hsn.mall.admin.util.ResponseUtil;
import com.hsn.mall.admin.vo.AdminVO;
import com.hsn.mall.core.model.AdminModel;
import com.hsn.mall.core.model.PermissionModel;
import com.hsn.mall.core.model.RoleModel;
import com.hsn.mall.core.request.AdminCreateDTO;
import com.hsn.mall.core.request.AdminListSearchDTO;
import com.hsn.mall.core.request.AdminUpdateDTO;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IAdminService;
import com.hsn.mall.core.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
@Permission("管理员操作")
public class AdminController {
    @Reference
    private IAdminService iAdminService;

    @Reference
    private IRoleService roleService;
    /**
     * 添加管理员
     * @param dto
     */
    @PostMapping("/add")
    @Permission("添加")
    public ResponseResult add(@RequestBody AdminCreateDTO dto){
        AdminModel byUserName = iAdminService.getByUserName(dto.getUsername());
        if(byUserName != null){
            return ResponseUtil.fail("用户名已存在!");
        }
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

    /**
     * 管理员用户查询
     */
    @GetMapping("/list")
    @Permission("查询")
    public PageResponse<AdminVO> list(@RequestBody AdminListSearchDTO dto){
        QueryWrapper<AdminModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",dto.getKeyWord());
        Page<AdminModel> page = new Page<>(dto.getCurrent(),dto.getSize());
        Page<AdminModel> search = iAdminService.page(page, queryWrapper);
        return PageUtil.convert(search,AdminVO.class);
    }

    /**
     * 更新管理员
     */
    @PostMapping("/update")
    @Permission("更新")
    public ResponseResult update(@RequestBody AdminUpdateDTO dto){
        AdminModel model = new AdminModel();
        BeanUtils.copyProperties(dto,model);
        iAdminService.updateById(model);
        return ResponseUtil.success("更新成功！");
    }

    /**
     * 禁启用账号
     */
    @GetMapping("/updateStatus")
    @Permission("禁启用账号")
    public ResponseResult updateStatus(
            @RequestParam String ids,@RequestParam Boolean status){
        if (StringUtils.isNoneBlank(ids)){
            String[] split = ids.split(",");
            List<AdminModel> modelList = new ArrayList<>(split.length);
            for (String s : split) {
                modelList.add(new AdminModel().setId(Integer.parseInt(s)).setDisabled(status));
            }
            iAdminService.updateBatchById(modelList);
        }
        return ResponseUtil.success("更新成功！");
    }
    /**
     * 通过id删除用户
     */
    @GetMapping("/delete/{userId}")
    @Permission("删除")
    public ResponseResult delete(@PathVariable("userId") Integer id){
       iAdminService.removeById(id);
       return ResponseUtil.success("删除成功!");
    }

    /**
     * 根据userId获取已有的角色
     */
    @GetMapping("/selectRoleByUserId/{id}")
    @Permission("根据id获取角色")
    public List<RoleModel> selectRoleByUserId(@PathVariable Integer id){
        AdminModel user = iAdminService.getById(id);
        List<RoleModel> list = null;
        if (StringUtils.isNotEmpty(user.getRoleIds())){
            list=roleService.listByIds(Arrays.asList(user.getRoleIds().split(",")));
        }
        return list;
    }
}

