package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_record
 *
 * @author
 */
@Data
public class Record implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 搜索次数
     */
    private Integer frequency;

    /**
     * 搜索时间
     */
    private Date searchtime;

    private static final long serialVersionUID = 1L;
}