package com.whoai.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基本属性
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
public abstract class BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 1494885873168556792L;
    /**
     * 实体对象id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建的用户id
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 修改人-名称
     */
    @TableField(value = "create_user",fill = FieldFill.UPDATE)
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_created", fill = FieldFill.INSERT)
    private Date gmtCreated;

    /**
     * 是否有效: [0:失效, 1:有效]
     */
    @TableField(value = "active", fill = FieldFill.INSERT_UPDATE)
    @TableLogic(value = "1", delval = "0")
    private Integer active;

}
