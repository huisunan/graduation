package com.hsn.mall.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.admin.annotation.Permission;
import com.hsn.mall.admin.bean.ResponseResult;
import com.hsn.mall.admin.util.PageUtil;
import com.hsn.mall.admin.util.ResponseUtil;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.StorageModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IStorageService;
import com.hsn.mall.core.storage.Storage;
import com.hsn.mall.core.storage.StorageProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 文件存储表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/storage")
@Permission("文件管理")
public class StorageController {
    @Autowired
    private StorageProvider storageProvider;

    @Autowired
    private IStorageService storageService;

    @PostMapping("/list")
    @Permission("获取文件列表")
    public PageResponse<?> list(@RequestBody BaseSearchBean baseSearchBean){
        QueryWrapper<StorageModel> query = new QueryWrapper<>();
        String keyWord = baseSearchBean.getKeyWord();
        if (StringUtils.isNotBlank(keyWord)) {
            query.like("key",keyWord);
            query.like("name",keyWord);
        }
        Page<StorageModel> page = PageUtil.create(baseSearchBean);
        return PageUtil.convert(storageService.page(page,query));
    }

    @PostMapping("/create")
    @Permission("文件上传")
    public StorageProvider.MallStorage create(
            @RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        StorageProvider.MallStorage store = storageProvider.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        return store;
    }

    @Permission("详情")
    @GetMapping("/read")
    public StorageModel read(@NotNull Integer id) {
        StorageModel model = storageService.getById(id);
        return model;
    }

    @Permission("删除")
    @GetMapping("/delete")
    public ResponseResult delete(String keys,String ids) {
        if (StringUtils.isNotBlank(keys) && StringUtils.isNotBlank(ids)){
            List<String> keyList = Arrays.asList(keys.split(","));
            keyList.forEach(storageProvider::delete);
            storageService.removeByIds(Arrays.asList(ids.split(",")));
        }
        return ResponseUtil.success("删除成功!");
    }
}

