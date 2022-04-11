package com.whoai.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论: tb_contact
 *
 * @author
 */
@Data
public class Contact implements Serializable {
    private static final long serialVersionUID = 7846847220588970315L;

    /**
     * 主键标识
     */
    private Integer id;

    /**
     * 文章英文标识
     */
    private String enArticle;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 评论标题
     */
    private String title;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论状态
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