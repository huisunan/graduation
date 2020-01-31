package com.hsn.mall.admin.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hsn.mall.admin.util.RedisUtil;
import com.hsn.mall.core.constants.MallConstant;
import com.hsn.mall.core.model.RegionModel;
import com.hsn.mall.core.service.IRegionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

/**
 * <p>
 * 行政区域表 前端控制器
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/region")
public class RegionController {
    @Reference
    private IRegionService regionService;

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    @PostMapping("list")
    public List<RegionModel> list(){
        String cache = (String) redisUtil.get(MallConstant.REGION);
        List<RegionModel> res = new ArrayList<>();
        if (cache == null || StringUtils.isEmpty(cache)){
            Map<Integer, RegionModel> all = regionService.all();
            for (Map.Entry<Integer,RegionModel> entry : all.entrySet()){
                RegionModel value = entry.getValue();
                if (value.getPid() == 0){
                    res.add(value);
                }else {
                    RegionModel parent = all.get(value.getPid());
                    if (parent.getChildren() == null){
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(value);
                }
            }
            redisUtil.set(MallConstant.REGION, JSONObject.toJSONString(res));
        }else {
            res = JSONObject.parseObject(cache,new TypeReference<List<RegionModel>>(){});
        }
        return res;
    }
}

