package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.core.model.AdvertisementModel;
import com.hsn.mall.core.request.search.AdvertisementSearchBean;
import com.hsn.mall.core.response.PageResponse;
import com.hsn.mall.core.service.IAdvertisementService;
import com.hsn.mall.core.util.PageUtil;
import com.hsn.mall.core.util.ResponseUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 广告表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/ad")
public class AdvertisementController {
    @Reference
    private IAdvertisementService adService;


    @PostMapping("/list")
    public PageResponse<AdvertisementModel> list(@RequestBody AdvertisementSearchBean searchBean) {

        return PageUtil.convert(adService.page(searchBean));
    }

    private Object validate(AdvertisementModel ad) {
        String name = ad.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.fail("名称不能为空!");
        }
        String content = ad.getContent();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.fail("内容不能为空!");
        }
        return null;
    }

    @PostMapping("/create")
    public Object create(@RequestBody AdvertisementModel ad) {
        Object error = validate(ad);
        if (error != null) {
            return error;
        }
//        if (ad.getStartTime() == null){
//            ad.setStartTime(LocalDateTime.now());
//        }
//        if (ad.getEndTime() == null){
//            ad.setEndTime(LocalDateTime.now());
//        }
        adService.save(ad);
        return ResponseUtil.add();
    }

    @GetMapping("/read")
    public AdvertisementModel read(@NotNull Integer id) {
        return adService.getById(id);
    }

    @PostMapping("/update")
    public Object update(@RequestBody AdvertisementModel ad) {
        Object error = validate(ad);
        if (error != null) {
            return error;
        }
//        if (ad.getStartTime() == null){
//            ad.setStartTime(LocalDateTime.now());
//        }
//        if (ad.getEndTime() == null){
//            ad.setEndTime(LocalDateTime.now());
//        }
        if (!adService.updateById(ad)) {
            return ResponseUtil.fail();
        }

        return ResponseUtil.update();
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody AdvertisementModel ad) {
        Integer id = ad.getId();
        if (id == null) {
            return ResponseUtil.fail();
        }
        adService.removeById(id);
        return ResponseUtil.delete();
    }

}

