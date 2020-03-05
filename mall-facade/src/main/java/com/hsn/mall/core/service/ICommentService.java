package com.hsn.mall.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsn.mall.core.model.CommentModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsn.mall.core.request.search.CommentSearchBean;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface ICommentService extends IService<CommentModel> {
    Page<CommentModel> page(CommentSearchBean searchBean);
}
