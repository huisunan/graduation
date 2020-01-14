package com.hsn.mall.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 类目表
 * </p>
 *
 * @author huisunan
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("litemall_category")
public class CategoryModel extends Model<CategoryModel> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 类目关键字，以JSON数组格式
     */
    private String keywords;

    /**
     * 类目广告语介绍
     */
    private String desc;

    /**
     * 父类目ID
     */
    private Integer pid;

    /**
     * 类目图标
     */
    private String iconUrl;

    /**
     * 类目图片
     */
    private String picUrl;

    private String level;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
