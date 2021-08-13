package com.whoai.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 字典表
 * - 导航分类（optional）
 * - 文章分类
 * - 标签分类
 */
@Data
@Entity
@Table(name = "tb_dictionary", indexes = {@Index(name = "unique_key", columnList = "dic_name,dic_key", unique = true)})
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1856130061638683738L;

    /**
     * 主键标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 字典名称
     */
    @Column(name = "dic_name", nullable = false)
    private String dicName;

    /**
     * 字典项
     * 同一个字典中不能存在相同的字典项
     * 不同字典中可以存在相同的字典项
     */
    @Column(name = "dic_key", nullable = false)
    private String dicKey;

    /**
     * 字典项值
     */
    @Column(name = "dic_value", nullable = false)
    private String dicValue;

    /**
     * 字典项顺序
     */
    @Column(name = "sequence")
    private Integer sequence;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

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
