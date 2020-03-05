package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.admin.annotation.Permission;
import com.hsn.mall.core.bean.ResponseResult;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.admin.util.PasswordUtil;
import com.hsn.mall.admin.util.SubjectUtil;
import com.hsn.mall.admin.vo.AdminVO;
import com.hsn.mall.admin.vo.RoleAllVO;
import com.hsn.mall.core.model.AdminModel;
import com.hsn.mall.core.model.RoleModel;
import com.hsn.mall.core.request.create.AdminCreateDTO;
import com.hsn.mall.core.request.search.AdminSearchDTO;
import com.hsn.mall.core.request.update.AdminUpdateDTO;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IAdminService;
import com.hsn.mall.core.service.IRoleService;
import com.hsn.mall.core.util.ResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        dto.setPassword(PasswordUtil.addSalt(dto.getPassword(),dto.getUsername()));
        iAdminService.save(dto);
        return ResponseUtil.success("添加成功!");
    }

    /**
     * 管理员用户查询
     */
    @PostMapping("/list")
    @Permission("查询")
    public PageResponse<AdminVO> list(@RequestBody AdminSearchDTO dto){
        Page<AdminModel> search = iAdminService.page(dto);
        PageResponse<AdminVO> vos = PageUtil.convert(search,AdminVO.class);
        vos.setRecords(new ArrayList<>(vos.getRecords().size()));
        for (AdminModel record : search.getRecords()) {
            AdminVO adminVO = new AdminVO();
            BeanUtils.copyProperties(record,adminVO);
            if (CollectionUtils.isNotEmpty(record.getRoleList())){
                record.getRoleList().forEach(i->adminVO.getRoles().add(
                        new RoleAllVO().setValue(i.getId()).setLabel(i.getName())
                ));
            }
            vos.getRecords().add(adminVO);
        }

        return vos;
    }

    /**
     * 更新管理员
     */
    @PostMapping("/update")
    @Permission("更新")
    public ResponseResult update(@RequestBody AdminUpdateDTO dto){
        dto.setPassword(PasswordUtil.addSalt(dto.getPassword(),dto.getUsername()));
        iAdminService.updateById(dto);
        return ResponseUtil.success("更新成功！");
    }

    /**
     * 禁启用账号
     */
    @GetMapping("/updateStatus")
    @Permission("禁启用账号")
    public ResponseResult updateStatus(
            @RequestParam Integer id,@RequestParam Boolean status){
        AdminModel model = new AdminModel().setId(id).setDisabled(status);
        iAdminService.updateById(model);
        return ResponseUtil.success("更新成功！");
    }
    /**
     * 通过id删除用户
     */
    @GetMapping("/delete")
    @Permission("删除")
    public ResponseResult delete(@RequestParam Integer id){
       iAdminService.removeById(id);
       return ResponseUtil.success("删除成功!");
    }

    /**
     * 根据userId获取已有的角色
     */
    @GetMapping("/selectRoleByUserId")
    @Permission("根据id获取角色")
    public List<RoleModel> selectRoleByUserId(@RequestParam Integer id){
        AdminModel user = iAdminService.getById(id);
        List<RoleModel> list = null;
        if (user != null && CollectionUtils.isNotEmpty(user.getRoleList())){
            list = user.getRoleList();
        }
        return list;
    }

    @PostMapping("/changePassword")
    @Permission("用户更改密码")
    public ResponseResult changePassword(String oldVal,String newVal){
        ResponseResult result = ResponseUtil.success("修改密码成功");
        AdminModel model = iAdminService.getById(SubjectUtil.getUserId());
        String oldPassword = PasswordUtil.addSalt(oldVal, SubjectUtil.getUserName());
        String newPassword = PasswordUtil.addSalt(newVal,SubjectUtil.getUserName());
        if (!model.getPassword().equals(oldPassword)){
            result.fail("旧密码错误!");
        }
        iAdminService.updateById(model.setPassword(newPassword));
        return result;
    }

    @PostMapping("/updatePassword")
    @Permission("管理员更改密码")
    public ResponseResult updatePassword(Integer userId,String newVal){
        AdminModel model = iAdminService.getById(userId);
        String newPassword = PasswordUtil.addSalt(newVal, model.getUsername());
        iAdminService.updateById(model.setPassword(newPassword));
        return ResponseUtil.success("修改密码成功!");
    }
}

