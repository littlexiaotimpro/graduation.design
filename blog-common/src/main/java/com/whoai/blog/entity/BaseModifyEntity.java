package com.whoai.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 基本修改属性
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseModifyEntity extends BaseIdEntity{

    private static final long serialVersionUID = -1060464378030625001L;
    /**
     * 修改人
     */
    @TableField(value = "modify_by",fill = FieldFill.INSERT_UPDATE)
    private Long modifyBy;

    /**
     * 修改人-名称
     */
    @TableField(value = "modify_user",fill = FieldFill.INSERT_UPDATE)
    private String modifyUser;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modify",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModify;

}
