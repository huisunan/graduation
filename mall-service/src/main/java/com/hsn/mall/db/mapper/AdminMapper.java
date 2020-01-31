package com.hsn.mall.db.mapper;

import com.hsn.mall.core.model.AdminModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
public interface AdminMapper extends BaseMapper<AdminModel> {
    /**
     * 根据adminid删除所有角色
     * @param adminId
     * @return
     */
    int deleteAllRole(Integer adminId);

    int insertRole(@Param("id")Integer id,@Param("list") List<Integer> roles);
}
