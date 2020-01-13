package com.hsn.mall.core.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author huisunan
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("litemall_log")
public class LogModel extends Model<LogModel> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 操作用户
     */
    private String userName;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 操作分类
     */
    private Integer type;

    /**
     * 请求地址
     */
    private String action;

    /**
     * 操作状态,0：失败  1：成功
     */
    private Boolean status;

    /**
     * 操作结果，或者成功消息，或者失败消息
     */
    private String result;

    /**
     * 请求用时，单位：ms
     */
    private Integer useTime;

    /**
     * 补充信息
     */
    private String comment;

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
