package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.core.bean.ResponseResult;
import com.hsn.mall.core.model.KeywordModel;
import com.hsn.mall.core.request.search.KeywordSearchDTO;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IKeywordService;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.util.ResponseUtil;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 关键字表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/keyword")
public class KeywordController {
    @Reference
    private IKeywordService keywordService;

    @PostMapping("list")
    public PageResponse<KeywordModel> list(@RequestBody KeywordSearchDTO dto){
        return PageUtil.convert(keywordService.page(dto));
    }

    @PostMapping("add")
    public ResponseResult add(@RequestBody KeywordModel model){
        keywordService.save(model);
        return ResponseUtil.add();
    }
    @PostMapping("update")
    public ResponseResult update(@RequestBody KeywordModel model){
        keywordService.updateById(model);
        return ResponseUtil.update();
    }
    @GetMapping("delete")
    public ResponseResult delete(Integer id){
        keywordService.removeById(id);
        return ResponseUtil.delete();
    }
}

