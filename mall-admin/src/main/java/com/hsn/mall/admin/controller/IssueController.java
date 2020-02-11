package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.core.bean.BaseSearchBean;
import com.hsn.mall.core.bean.ResponseResult;
import com.hsn.mall.core.model.IssueModel;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IIssueService;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 常见问题表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/issue")
public class IssueController {
    @Reference
    private IIssueService iIssueService;

    @PostMapping("list")
    public PageResponse<IssueModel> list(@RequestBody BaseSearchBean searchBean){
        return PageUtil.convert(iIssueService.page(searchBean));
    }

    @PostMapping("add")
    public ResponseResult add(@RequestBody IssueModel model){
        iIssueService.save(model);
        return ResponseUtil.success("添加成功！");
    }

    @PostMapping("update")
    public ResponseResult update(@RequestBody IssueModel model){
        iIssueService.updateById(model);
        return ResponseUtil.success("更新成功！");
    }

    @GetMapping("delete")
    public ResponseResult delete(Integer id){
        iIssueService.removeById(id);
        return ResponseUtil.success("删除成功！");
    }
}

