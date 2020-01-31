package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.FootprintModel;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.FootprintMapper;
import com.hsn.mall.core.service.IFootprintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户浏览足迹表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IFootprintService.class)
public class FootprintServiceImpl extends ServiceImpl<FootprintMapper, FootprintModel> implements IFootprintService {

    @Override
    public Page<FootprintModel> page(BaseSearchBean searchBean) {
        QueryWrapper<FootprintModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getKeyword())){
            query.eq("user_id",searchBean.getKeyword());
        }
        return page(PageUtil.create(searchBean),query);
    }
}
