package com.hsn.mall.db.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hsn.mall.core.model.IssueModel;
import com.hsn.mall.db.mapper.IssueMapper;
import com.hsn.mall.core.service.IIssueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 常见问题表 服务实现类
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Component
@Service(interfaceClass = IIssueService.class)
public class IssueServiceImpl extends ServiceImpl<IssueMapper, IssueModel> implements IIssueService {

}
