package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.model.IssueModel;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.IssueMapper;
import com.hsn.mall.core.service.IIssueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 常见问题表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IIssueService.class)
public class IssueServiceImpl extends ServiceImpl<IssueMapper, IssueModel> implements IIssueService {

    @Override
    public Page<IssueModel> page(BaseSearchBean searchBean) {
        QueryWrapper<IssueModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getKeyword())){
            query.like("question",searchBean.getKeyword());
        }
        Page<IssueModel> page = PageUtil.create(searchBean);
        return page(page,query);
    }
}
