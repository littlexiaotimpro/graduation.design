package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_contact
 * @author 
 */
@Data
public class Contact implements Serializable {
    private static final long serialVersionUID = 7846847220588970315L;
    private Integer id;

    private String nickname;

    private String email;

    private String title;

    private String content;

    private Integer status;

    private Date senttime;

}