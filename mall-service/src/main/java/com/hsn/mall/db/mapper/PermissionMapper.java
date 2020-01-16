package com.hsn.mall.db.mapper;

import com.hsn.mall.core.model.PermissionModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface PermissionMapper extends BaseMapper<PermissionModel> {
    /**
     * 根据角色ID获取权限
     * @param roleIds ids
     * @return 权限
     */
    List<PermissionModel> selectByRoleIds(List<String> roleIds);
}
