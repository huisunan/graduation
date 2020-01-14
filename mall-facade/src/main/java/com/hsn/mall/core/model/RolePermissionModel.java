package com.hsn.mall.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("litemall_role_permission")
public class RolePermissionModel extends Model<RolePermissionModel> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
