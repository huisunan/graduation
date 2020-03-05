package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hsn.mall.core.model.BrandModel;
import com.hsn.mall.core.model.CategoryModel;
import com.hsn.mall.core.model.GoodsModel;
import com.hsn.mall.core.model.GoodsProductModel;
import com.hsn.mall.core.request.create.GoodsAllinone;
import com.hsn.mall.core.request.create.GoodsAllinoneDTO;
import com.hsn.mall.core.request.search.GoodsSearchBean;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IBrandService;
import com.hsn.mall.core.service.ICategoryService;
import com.hsn.mall.core.service.IGoodsService;
import com.hsn.mall.core.util.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/good")
public class GoodsController {
    @Reference
    private IGoodsService goodsService;

    @Reference
    private ICategoryService categoryService;

    @Reference
    private IBrandService brandService;

    @PostMapping("/list")
    public PageResponse<GoodsModel> list(@RequestBody GoodsSearchBean searchBean) {
        return PageUtil.convert(goodsService.page(searchBean));
    }

    @GetMapping("/catAndBrand")
    public Object list2() {
        // http://element-cn.eleme.io/#/zh-CN/component/cascader
        // 管理员设置“所属分类”
        List<CategoryModel> categoryList = new ArrayList<>();
        Map<Integer, CategoryModel> all = categoryService.all();
        for (Map.Entry<Integer, CategoryModel> a : all.entrySet()) {
            CategoryModel value = a.getValue();
            if (value.getPid() == 0){
                categoryList.add(value);
            }else {
                CategoryModel parent = all.get(value.getPid());
                if (parent.getChildren() == null){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(value);
            }
        }

        // http://element-cn.eleme.io/#/zh-CN/component/select
        // 管理员设置“所属品牌商”
        List<BrandModel> list = brandService.list();
        List<Map<String, Object>> brandList = new ArrayList<>(list.size());
        for (BrandModel brand : list) {
            Map<String, Object> b = new HashMap<>(2);
            b.put("value", brand.getId());
            b.put("label", brand.getName());
            brandList.add(b);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", categoryList);
        data.put("brandList", brandList);
        return data;
    }

    /**
     * 编辑商品
     *
     * @return
     */
    @RequiresPermissions("admin:goods:update")
    @PostMapping("/update")
    public Object update(@RequestBody GoodsAllinoneDTO dto) {
        GoodsAllinone allinone = dtoToModel(dto);

        return goodsService.update(allinone);
    }

    /**
     * 删除商品
     *
     * @return
     */
    @RequiresPermissions("admin:goods:delete")
    @PostMapping("/delete")
    public Object delete(String id) {
        return goodsService.removeById(id);
    }

    /**
     * 添加商品
     *
     * @param goodsAllinone
     * @return
     */
    @RequiresPermissions("admin:goods:create")
    @PostMapping("/create")
    public Object create(@RequestBody GoodsAllinoneDTO dto) {
        GoodsAllinone goodsAllinone = dtoToModel(dto);
        return goodsService.create(goodsAllinone);
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:goods:read")
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return goodsService.detail(id);

    }

    private GoodsAllinone dtoToModel(GoodsAllinoneDTO dto){
        GoodsAllinone goodsAllinone = new GoodsAllinone();
        BeanUtils.copyProperties(dto,goodsAllinone);

        GoodsModel goodsModel = new GoodsModel();
        BeanUtils.copyProperties(dto.getGoods(),goodsModel);
        goodsModel.setGallery(JSON.toJSONString(dto.getGoods().getGallery()));
        goodsAllinone.setGoods(goodsModel);

        GoodsProductModel[] productModels = new GoodsProductModel[dto.getProducts().length];
        for (int i = 0; i < dto.getProducts().length; i++) {
            productModels[i] = new GoodsProductModel();
            BeanUtils.copyProperties(dto.getProducts()[i],productModels[i]);
            productModels[i].setSpecifications(JSON.toJSONString(dto.getProducts()[i].getSpecifications()));
        }
        goodsAllinone.setProducts(productModels);
        return goodsAllinone;
    }
}

