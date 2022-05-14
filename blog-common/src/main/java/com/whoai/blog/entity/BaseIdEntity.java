package com.whoai.blog.entity;

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
    private Long id;

    /**
     * 创建的用户id
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 是否有效: [0:失效, 1:有效]
     */
    private Integer active;

}
