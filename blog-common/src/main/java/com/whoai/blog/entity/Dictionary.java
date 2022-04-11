package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表
 * - 导航分类（optional）
 * - 文章分类
 * - 标签分类
 */
@Data
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1856130061638683738L;

    /**
     * 主键标识
     */
    private Integer id;

    /**
     * 字典名称
     */
    private String dicName;

    /**
     * 字典项
     * 同一个字典中不能存在相同的字典项
     * 不同字典中可以存在相同的字典项
     */
    private String dicKey;

    /**
     * 字典项值
     */
    private String dicValue;

    /**
     * 字典项顺序
     */
    private Integer sequence;

    /**
     * 状态
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
