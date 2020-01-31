package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsn.mall.core.model.CategoryModel;
import com.hsn.mall.db.mapper.CategoryMapper;
import com.hsn.mall.core.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ICategoryService.class)
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryModel> implements ICategoryService {

    @Override
    public Map<Integer, CategoryModel> all() {
        return this.baseMapper.selectAll();
    }

    @Override
    public List<CategoryModel> queryL1() {
        QueryWrapper<CategoryModel> query = new QueryWrapper<>();
        query.eq("level","L1");
        return list(query);
    }
}
