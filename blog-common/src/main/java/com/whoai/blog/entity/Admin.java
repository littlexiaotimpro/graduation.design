package com.whoai.blog.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.whoai.blog.constant.Permission;
import com.whoai.blog.constant.Status;
import com.whoai.blog.dict.DictionaryJsonSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户: tb_admin
 *
 * @author
 */
@Data
@Entity
@Table(name = "tb_admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 7738212484595459219L;

    /**
     * 主键标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 账号
     */
    @Column(name = "account", unique = true, nullable = false)
    private String account;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 权限
     */
    @Column(name = "permission")
    @JsonSerialize(using = DictionaryJsonSerializer.class)
    private Permission permission;

    /**
     * 状态（0：禁用，1：启用）
     */
    @Column(name = "status")
    @JsonSerialize(using = DictionaryJsonSerializer.class)
    private Status status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}