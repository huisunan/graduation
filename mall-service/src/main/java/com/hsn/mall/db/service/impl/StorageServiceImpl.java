package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.StorageModel;
import com.hsn.mall.db.mapper.StorageMapper;
import com.hsn.mall.core.service.IStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 文件存储表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IStorageService.class)
public class StorageServiceImpl extends ServiceImpl<StorageMapper, StorageModel> implements IStorageService {

}
