package com.hsn.mall.db.service.impl;

import com.hsn.mall.core.model.CategoryModel;
import com.hsn.mall.db.mapper.CategoryMapper;
import com.hsn.mall.core.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryModel> implements ICategoryService {

}
