package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_book
 *
 * @author
 */
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = -4371363865991393409L;
    private String enbook;

    private String ennav;

    private String enarticle;

    private String encategory;

    private String entag;

    private String adminid;

    private String imgbook;

    private String caption;

    private String author;

    private String summary;

    private String address;

    private Integer status;

    private Date createtime;

    private Date updatetime;

}