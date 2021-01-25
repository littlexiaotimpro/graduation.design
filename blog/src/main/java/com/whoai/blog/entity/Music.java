package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_music
 *
 * @author
 */
@Data
public class Music implements Serializable {
    private String enmusic;

    private String enarticle;

    private String ennav;

    private String encategory;

    private String entag;

    private String adminid;

    private String imgmusic;

    private String caption;

    private String author;

    private String summary;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}