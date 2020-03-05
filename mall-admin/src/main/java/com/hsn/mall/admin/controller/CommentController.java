package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.core.bean.ResponseResult;
import com.hsn.mall.core.model.CommentModel;
import com.hsn.mall.core.request.search.CommentSearchBean;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.ICommentService;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Reference
    private ICommentService commentService;

    @GetMapping("/list")
    public PageResponse<CommentModel> list(@RequestBody CommentSearchBean searchBean) {
        return PageUtil.convert(commentService.page(searchBean));
    }

    @PostMapping("/delete")
    public ResponseResult delete(String id) {
        commentService.removeById(id);
        return ResponseUtil.delete();
    }

}

