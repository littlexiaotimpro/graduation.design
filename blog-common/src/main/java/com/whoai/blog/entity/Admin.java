package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_admin
 * @author 
 */
@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = 7738212484595459219L;

    /**
     * 主键标识
     */
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限
     */
    private Integer permission;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}