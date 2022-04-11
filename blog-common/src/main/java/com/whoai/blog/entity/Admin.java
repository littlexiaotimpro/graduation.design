package com.whoai.blog.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.whoai.blog.constant.Permission;
import com.whoai.blog.constant.Status;
import com.whoai.blog.dict.DictionaryJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户: tb_admin
 *
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
    @JsonSerialize(using = DictionaryJsonSerializer.class)
    private Permission permission;

    /**
     * 状态（0：禁用，1：启用）
     */
    @JsonSerialize(using = DictionaryJsonSerializer.class)
    private Status status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}