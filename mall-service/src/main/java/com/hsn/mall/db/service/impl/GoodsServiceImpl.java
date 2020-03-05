package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.*;
import com.hsn.mall.core.request.create.GoodsAllinone;
import com.hsn.mall.core.request.search.GoodsSearchBean;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.*;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.util.ResponseUtil;
import com.hsn.mall.db.mapper.CategoryMapper;
import com.hsn.mall.db.mapper.GoodsAttributeMapper;
import com.hsn.mall.db.mapper.GoodsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsn.mall.db.mapper.GoodsSpecificationMapper;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IGoodsService.class)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsModel> implements IGoodsService {

    @Reference
    private IBrandService brandService;

    @Reference
    private ICategoryService categoryService;

    @Reference
    private IGoodsSpecificationService specificationService;

    @Reference
    private IGoodsProductService productService;

    @Reference
    private IGoodsAttributeService attributeService;

    @Reference
    private ICartService cartService;

    @Override
    public Page<GoodsModel> page(GoodsSearchBean searchBean) {
        QueryWrapper<GoodsModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getName())) {
            query.like("name", searchBean.getName());
        }
        if (StringUtils.isNotBlank(searchBean.getGoodsSn())) {
            query.eq("goodsSn", searchBean.getGoodsSn());
        }
        if (searchBean.getGoodsId() != null) {
            query.eq("id", searchBean.getGoodsId());
        }
        Page<GoodsModel> page = page(PageUtil.create(searchBean), query);
        for (GoodsModel record : page.getRecords()) {
            if (StringUtils.isNotBlank(record.getGallery())){
                String[] array = JSON.parseArray(record.getGallery(), String.class).toArray(new String[]{});
                record.setGalleryArray(array);
            }
        }
        return page;
    }

    /**
     * 编辑商品
     * <p>
     * NOTE：
     * 由于商品涉及到四个表，特别是litemall_goods_product表依赖litemall_goods_specification表，
     * 这导致允许所有字段都是可编辑会带来一些问题，因此这里商品编辑功能是受限制：
     * （1）litemall_goods表可以编辑字段；
     * （2）litemall_goods_specification表只能编辑pic_url字段，其他操作不支持；
     * （3）litemall_goods_product表只能编辑price, number和url字段，其他操作不支持；
     * （4）litemall_goods_attribute表支持编辑、添加和删除操作。
     * <p>
     * NOTE2:
     * 前后端这里使用了一个小技巧：
     * 如果前端传来的update_time字段是空，则说明前端已经更新了某个记录，则这个记录会更新；
     * 否则说明这个记录没有编辑过，无需更新该记录。
     * <p>
     * NOTE3:
     * （1）购物车缓存了一些商品信息，因此需要及时更新。
     * 目前这些字段是goods_sn, goods_name, price, pic_url。
     * （2）但是订单里面的商品信息则是不会更新。
     * 如果订单是未支付订单，此时仍然以旧的价格支付。
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Object update(GoodsAllinone goodsAllinone) {
        Object error = validate(goodsAllinone);
        if (error != null) {
            return error;
        }

        GoodsModel goods = goodsAllinone.getGoods();
        GoodsAttributeModel[] attributes = goodsAllinone.getAttributes();
        GoodsSpecificationModel[] specifications = goodsAllinone.getSpecifications();
        GoodsProductModel[] products = goodsAllinone.getProducts();

        //将生成的分享图片地址写入数据库
//        String url = qCodeService.createGoodShareImage(goods.getId().toString(), goods.getPicUrl(), goods.getName());
//        goods.setShareUrl(url);

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (GoodsProductModel product : products) {
            BigDecimal productPrice = product.getPrice();
            if (retailPrice.compareTo(productPrice) > 0) {
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);

        // 商品基本信息表litemall_goods
        if (!this.updateById(goods)) {
            throw new RuntimeException("更新数据失败");
        }

        Integer gid = goods.getId();

        // 商品规格表litemall_goods_specification
        for (GoodsSpecificationModel specification : specifications) {
            // 目前只支持更新规格表的图片字段
            if (specification.getUpdateTime() == null) {
                specification.setSpecification(null);
                specification.setValue(null);
                specificationService.updateById(specification);
            }
        }

        // 商品货品表litemall_product
        for (GoodsProductModel product : products) {
            if (product.getUpdateTime() == null) {
                productService.updateById(product);
            }
        }

        // 商品参数表litemall_goods_attribute
        for (GoodsAttributeModel attribute : attributes) {
            if (attribute.getId() == null || attribute.getId().equals(0)) {
                attribute.setGoodsId(goods.getId());
                attributeService.save(attribute);
            } else if (attribute.getDeleted()) {
                attributeService.removeById(attribute.getId());
            } else if (attribute.getUpdateTime() == null) {
                attributeService.updateById(attribute);
            }
        }

        // 这里需要注意的是购物车litemall_cart有些字段是拷贝商品的一些字段，因此需要及时更新
        // 目前这些字段是goods_sn, goods_name, price, pic_url
        for (GoodsProductModel product : products) {
            cartService.updateProduct(product.getId(), goods.getGoodsSn(), goods.getName(), product.getPrice(), product.getUrl());
        }
        return ResponseUtil.success("更新成功！");
    }

    @Override
    public Object create(GoodsAllinone goodsAllinone) {
        Object error = validate(goodsAllinone);
        if (error != null) {
            return error;
        }

        GoodsModel goods = goodsAllinone.getGoods();
        GoodsAttributeModel[] attributes = goodsAllinone.getAttributes();
        GoodsSpecificationModel[] specifications = goodsAllinone.getSpecifications();
        GoodsProductModel[] products = goodsAllinone.getProducts();

        String name = goods.getName();
        if (this.checkExistByName(name)) {
            return ResponseUtil.fail("商品名已经存在");
        }

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (GoodsProductModel product : products) {
            BigDecimal productPrice = product.getPrice();
            if (retailPrice.compareTo(productPrice) == 1) {
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);

        // 商品基本信息表litemall_goods
        this.save(goods);

//        //将生成的分享图片地址写入数据库
//        String url = qCodeService.createGoodShareImage(goods.getId().toString(), goods.getPicUrl(), goods.getName());
//        if (!org.springframework.util.StringUtils.isEmpty(url)) {
//            goods.setShareUrl(url);
//            if (goodsService.updateById(goods) == 0) {
//                throw new RuntimeException("更新数据失败");
//            }
//        }

        // 商品规格表litemall_goods_specification
        for (GoodsSpecificationModel specification : specifications) {
            specification.setGoodsId(goods.getId());
            specificationService.save(specification);
        }

        // 商品参数表litemall_goods_attribute
        for (GoodsAttributeModel attribute : attributes) {
            attribute.setGoodsId(goods.getId());
            attributeService.save(attribute);
        }

        // 商品货品表litemall_product
        for (GoodsProductModel product : products) {
            product.setGoodsId(goods.getId());
            productService.save(product);
        }
        return ResponseUtil.success("添加成功！");
    }

    @Override
    public Object detail(Integer id) {
        GoodsModel goods = this.getById(id);
        List<GoodsProductModel> products = productService.queryByGid(id);
        List<GoodsSpecificationModel> specifications = specificationService.queryByGid(id);
        List<GoodsAttributeModel> attributes = attributeService.queryByGid(id);

        Integer categoryId = goods.getCategoryId();
        CategoryModel category = categoryService.getById(categoryId);
        Integer[] categoryIds = new Integer[]{};
        if (category != null) {
            Integer parentCategoryId = category.getPid();
            categoryIds = new Integer[]{parentCategoryId, categoryId};
        }

        Map<String, Object> data = new HashMap<>();
        data.put("goods", goods);
        data.put("specifications", specifications);
        data.put("products", products);
        data.put("attributes", attributes);
        data.put("categoryIds", categoryIds);
        return data;
    }

    public boolean checkExistByName(String name) {
        QueryWrapper<GoodsModel> query = new QueryWrapper<>();
        query.eq("name", name);
        return count(query) != 0;
    }

    private Object validate(GoodsAllinone goodsAllinone) {
        GoodsModel goods = goodsAllinone.getGoods();
        String name = goods.getName();
        if (org.springframework.util.StringUtils.isEmpty(name)) {
            return ResponseUtil.fail();
        }
        String goodsSn = goods.getGoodsSn();
        if (org.springframework.util.StringUtils.isEmpty(goodsSn)) {
            return ResponseUtil.fail();
        }
        // 品牌商可以不设置，如果设置则需要验证品牌商存在
        Integer brandId = goods.getBrandId();
        if (brandId != null && brandId != 0) {
            if (brandService.getById(brandId) == null) {
                return ResponseUtil.fail();
            }
        }
        // 分类可以不设置，如果设置则需要验证分类存在
        Integer categoryId = goods.getCategoryId();
        if (categoryId != null && categoryId != 0) {
            if (categoryService.getById(categoryId) == null) {
                return ResponseUtil.fail();
            }
        }

        GoodsAttributeModel[] attributes = goodsAllinone.getAttributes();
        for (GoodsAttributeModel attribute : attributes) {
            String attr = attribute.getAttribute();
            if (org.springframework.util.StringUtils.isEmpty(attr)) {
                return ResponseUtil.fail();
            }
            String value = attribute.getValue();
            if (org.springframework.util.StringUtils.isEmpty(value)) {
                return ResponseUtil.fail();
            }
        }

        GoodsSpecificationModel[] specifications = goodsAllinone.getSpecifications();
        for (GoodsSpecificationModel specification : specifications) {
            String spec = specification.getSpecification();
            if (org.springframework.util.StringUtils.isEmpty(spec)) {
                return ResponseUtil.fail();
            }
            String value = specification.getValue();
            if (org.springframework.util.StringUtils.isEmpty(value)) {
                return ResponseUtil.fail();
            }
        }

        GoodsProductModel[] products = goodsAllinone.getProducts();
        for (GoodsProductModel product : products) {
            Integer number = product.getNumber();
            if (number == null || number < 0) {
                return ResponseUtil.fail();
            }

            BigDecimal price = product.getPrice();
            if (price == null) {
                return ResponseUtil.fail();
            }

            String[] productSpecifications =JSON.parseArray(product.getSpecifications(),String.class).toArray(new String[]{});
            if (productSpecifications.length == 0) {
                return ResponseUtil.fail();
            }
        }

        return null;
    }
}
