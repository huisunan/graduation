package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.CollectModel;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.CollectMapper;
import com.hsn.mall.core.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ICollectService.class)
public class CollectServiceImpl extends ServiceImpl<CollectMapper, CollectModel> implements ICollectService {

    @Override
    public Page<CollectModel> page(BaseSearchBean searchBean) {
        QueryWrapper<CollectModel> query = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(searchBean.getKeyword())){
            query.eq("user_id",searchBean.getKeyword());
        }
        Page<CollectModel> page = PageUtil.create(searchBean);
        return page(page,query);
    }
}
