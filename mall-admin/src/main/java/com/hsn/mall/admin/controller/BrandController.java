package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.ResponseResult;
import com.hsn.mall.admin.util.ResponseUtil;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.BrandModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IBrandService;
import com.hsn.mall.core.util.PageUtil;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 品牌商表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private IBrandService brandService;

    @PostMapping("/list")
    public PageResponse<BrandModel> list(@RequestBody BaseSearchBean searchBean){
        Page<BrandModel> page = brandService.page(searchBean);
        return PageUtil.convert(page);
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody BrandModel model){
        brandService.save(model);
        return ResponseUtil.success("添加成功");
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody BrandModel model){
        brandService.updateById(model);
        return ResponseUtil.success("修改成功");
    }

    @GetMapping("/delete")
    public ResponseResult update(Integer id){
        brandService.removeById(id);
        return ResponseUtil.success("删除成功");
    }
}

