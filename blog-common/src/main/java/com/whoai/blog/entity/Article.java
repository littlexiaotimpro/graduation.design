package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_article
 *
 * @author
 */
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 1011874484610243604L;

    private String enarticle;

    private String ennav;

    private String encategory;

    private String entag;

    private String adminid;

    private String title;

    private String author;

    private String summary;

    private String fileurl;

    private Integer readnum;

    private Integer status;

    private Date createtime;

    private Date updatetime;

}