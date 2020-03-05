package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.CommentModel;
import com.hsn.mall.core.request.search.CommentSearchBean;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.db.mapper.CommentMapper;
import com.hsn.mall.core.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ICommentService.class)
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentModel> implements ICommentService {

    @Override
    public Page<CommentModel> page(CommentSearchBean searchBean) {
        QueryWrapper<CommentModel> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchBean.getUserId())){
            query.eq("user_id",searchBean.getUserId());
        }
        if (StringUtils.isNotBlank(searchBean.getValueId())){
            query.eq("value_id",searchBean.getValueId());
        }
        return page(PageUtil.create(searchBean),query);
    }
}
