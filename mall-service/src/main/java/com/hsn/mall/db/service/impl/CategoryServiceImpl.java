package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.CategoryModel;
import com.hsn.mall.db.mapper.CategoryMapper;
import com.hsn.mall.core.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

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

}
