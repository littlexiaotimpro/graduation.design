package com.whoai.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文本内容: tb_article
 *
 * @author
 */
@Data
@Entity
@Table(name = "tb_article")
public class Article implements Serializable {
    private static final long serialVersionUID = 1011874484610243604L;

    /**
     * 主键标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 英文标识
     */
    @Column(name = "en_article", unique = true, nullable = false)
    private String enArticle;

    /**
     * 所属一级分类
     */
    @Column(name = "en_nav")
    private String enNav;

    /**
     * 所属二级分类
     */
    @Column(name = "en_category")
    private String enCategory;

    /**
     * 标签，多标签用逗号隔开
     */
    @Column(name = "en_tag")
    private String enTag;

    /**
     * 上传者
     */
    @Column(name = "admin_id")
    private String adminId;

    /**
     * 文章标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 文章作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 文章摘要
     */
    @Column(name = "summary")
    private String summary;

    /**
     * 文章地址
     */
    @Column(name = "file_url")
    private String fileUrl;

    /**
     * 阅读量
     */
    @Column(name = "read_num")
    private Integer readNum;

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