package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文本内容: tb_article
 *
 * @author
 */
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 1011874484610243604L;

    /**
     * 主键标识
     */
    private Integer id;

    /**
     * 英文标识
     */
    private String enArticle;

    /**
     * 所属一级分类
     */
    private String enNav;

    /**
     * 所属二级分类
     */
    private String enCategory;

    /**
     * 标签，多标签用逗号隔开
     */
    private String enTag;

    /**
     * 上传者
     */
    private String adminId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章地址
     */
    private String fileUrl;

    /**
     * 阅读量
     */
    private Integer readNum;

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