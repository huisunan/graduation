package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.admin.annotation.Permission;
import com.hsn.mall.core.bean.ResponseResult;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.StorageModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IStorageService;
import com.hsn.mall.core.storage.StorageProvider;
import com.hsn.mall.core.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Reference
    private IStorageService storageService;

    @PostMapping("/list")
    @Permission("获取文件列表")
    public PageResponse<?> list(@RequestBody BaseSearchBean baseSearchBean){
        return PageUtil.convert(storageService.page(baseSearchBean));
    }

    @PostMapping("/upload")
    @Permission("文件上传")
    public StorageProvider.MallStorage create(
            @RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        StorageProvider.MallStorage store = storageProvider.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        StorageModel model = new StorageModel();
        BeanUtils.copyProperties(store,model);
        storageService.save(model);
        return store;
    }

    @Permission("详情")
    @GetMapping("/detail")
    public StorageModel read(@RequestParam Integer id) {
        return storageService.getById(id);
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

