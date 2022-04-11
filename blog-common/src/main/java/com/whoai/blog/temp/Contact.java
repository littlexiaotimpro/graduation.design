//package com.whoai.blog.temp;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * 评论: tb_contact
// *
// * @author
// */
//@Data
//@Entity
//@Table(name = "tb_contact", indexes = {@Index(name = "FK_EN_Article", columnList = "en_article")})
//public class Contact implements Serializable {
//    private static final long serialVersionUID = 7846847220588970315L;
//
//    /**
//     * 主键标识
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    /**
//     * 文章英文标识
//     */
//    @Column(name = "en_article")
//    private String enArticle;
//
//    /**
//     * 昵称
//     */
//    @Column(name = "nickname", unique = true, nullable = false)
//    private String nickname;
//
//    /**
//     * 邮箱
//     */
//    @Column(name = "email", unique = true, nullable = false)
//    private String email;
//
//    /**
//     * 评论标题
//     */
//    @Column(name = "title")
//    private String title;
//
//    /**
//     * 评论内容
//     */
//    @Column(name = "content")
//    private String content;
//
//    /**
//     * 评论状态
//     */
//    @Column(name = "status")
//    private Integer status;
//
//    /**
//     * 创建时间
//     */
//    @Column(name = "create_time")
//    private Date createTime;
//
//    /**
//     * 更新时间
//     */
//    @Column(name = "update_time")
//    private Date updateTime;
//
//}