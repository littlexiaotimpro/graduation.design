package com.whoai.blog.entity;

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
    private Long modifyBy;

    /**
     * 修改时间
     */
    private Date gmtModify;

}
