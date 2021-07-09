package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_media
 *
 * @author
 */
@Data
public class Media implements Serializable {
    private static final long serialVersionUID = 1703596116169826280L;
    private String enmedia;

    private String enarticle;

    private String ennav;

    private String encategory;

    private String entag;

    private String adminid;

    private String imgmedia;

    private String caption;

    private String showtime;

    private String summary;

    private String bluray720;

    private String bluray1080;

    private String bluraydisk;

    private Integer status;

    private Date createtime;

    private Date updatetime;

}