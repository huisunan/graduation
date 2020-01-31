package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.StorageModel;
import com.hsn.mall.db.mapper.StorageMapper;
import com.hsn.mall.core.service.IStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 文件存储表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IStorageService.class)
public class StorageServiceImpl extends ServiceImpl<StorageMapper, StorageModel> implements IStorageService {

    @Override
    public Page<StorageModel> page(BaseSearchBean baseSearchBean) {
        QueryWrapper<StorageModel> query = new QueryWrapper<>();
        String keyWord = baseSearchBean.getKeyword();
        if (StringUtils.isNotBlank(keyWord)) {
            query.like("key",keyWord);
            query.like("name",keyWord);
        }
        Page<StorageModel> page = new Page<>();
        BeanUtils.copyProperties(baseSearchBean,page);
        return page(page,query);
    }
}
