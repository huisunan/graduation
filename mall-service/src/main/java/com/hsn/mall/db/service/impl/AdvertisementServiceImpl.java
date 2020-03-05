package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.AdvertisementModel;
import com.hsn.mall.core.request.search.AdvertisementSearchBean;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.AdvertisementMapper;
import com.hsn.mall.core.service.IAdvertisementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 广告表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IAdvertisementService.class)
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, AdvertisementModel> implements IAdvertisementService {

    @Override
    public Page<AdvertisementModel> page(AdvertisementSearchBean searchBean) {
        QueryWrapper<AdvertisementModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getName())){
            query.like("name",searchBean.getName());
        }
        if (StringUtils.isNotBlank(searchBean.getContent())){
            query.like("content", searchBean.getContent());
        }
        return page(PageUtil.create(searchBean),query);
    }
}
