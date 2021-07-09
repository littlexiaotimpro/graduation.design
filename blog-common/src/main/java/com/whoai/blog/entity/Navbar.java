package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_navbar
 *
 * @author
 */
@Data
public class Navbar implements Serializable {
    private static final long serialVersionUID = -8453393245807340273L;
    /**
     * 导航标识
     */
    private String ennav;

    /**
     * 名称
     */
    private String caption;

    /**
     * 序号
     */
    private Integer navlevel;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;


}