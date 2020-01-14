package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.CommentModel;
import com.hsn.mall.db.mapper.CommentMapper;
import com.hsn.mall.core.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
