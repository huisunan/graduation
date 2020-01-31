package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.admin.bean.ResponseResult;
import com.hsn.mall.admin.util.ResponseUtil;
import com.hsn.mall.core.model.CategoryModel;
import com.hsn.mall.core.service.ICategoryService;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Reference
    private ICategoryService categoryService;

    @PostMapping("/list")
    public List<CategoryModel> list() {
        List<CategoryModel> res = new ArrayList<>();
        Map<Integer, CategoryModel> all = categoryService.all();
        for (Map.Entry<Integer, CategoryModel> a : all.entrySet()) {
            CategoryModel value = a.getValue();
            if (value.getPid() == 0){
                res.add(value);
            }else {
                CategoryModel parent = all.get(value.getPid());
                if (parent.getChildren() == null){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(value);
            }
        }
        return res;
    }

    @GetMapping("/l1")
    public List<Map<String, Object>> catL1() {
        // 所有一级分类目录
        List<CategoryModel> l1CatList = categoryService.queryL1();
        List<Map<String, Object>> data = new ArrayList<>(l1CatList.size());
        for (CategoryModel category : l1CatList) {
            Map<String, Object> d = new HashMap<>(2);
            d.put("value", category.getId());
            d.put("label", category.getName());
            data.add(d);
        }
        return data;
    }

    @GetMapping("/delete")
    public ResponseResult delete(Integer id) {
        categoryService.removeById(id);
        return ResponseUtil.success("删除成功!");
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody CategoryModel category) {
        categoryService.updateById(category);
        return ResponseUtil.success("更新成功!");
    }

    @GetMapping("/detail")
    public CategoryModel read(Integer id) {
        return categoryService.getById(id);
    }

    @PostMapping("/add")
    public Object add(@RequestBody CategoryModel category) {
        categoryService.save(category);
        return ResponseUtil.success("添加成功!");
    }
}

