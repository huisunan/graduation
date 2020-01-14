package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.LogModel;
import com.hsn.mall.db.mapper.LogMapper;
import com.hsn.mall.core.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = ILogService.class)
public class LogServiceImpl extends ServiceImpl<LogMapper, LogModel> implements ILogService {

}
