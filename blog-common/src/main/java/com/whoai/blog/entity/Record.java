package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 搜索记录: tb_record
 *
 * @author
 */
@Data
public class Record implements Serializable {
    private static final long serialVersionUID = -9066330380051214456L;
    /**
     * 编号，主键标识
     */
    private Integer id;

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 搜索频率
     */
    private Integer frequency;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}