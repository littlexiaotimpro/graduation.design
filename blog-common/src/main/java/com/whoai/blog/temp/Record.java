//package com.whoai.blog.temp;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * 搜索记录: tb_record
// *
// * @author
// */
//@Data
//@Entity
//@Table(name = "tb_record")
//public class Record implements Serializable {
//    private static final long serialVersionUID = -9066330380051214456L;
//    /**
//     * 编号，主键标识
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    /**
//     * 搜索关键字
//     */
//    @Column(name = "keyword")
//    private String keyword;
//
//    /**
//     * 搜索频率
//     */
//    @Column(name = "frequency")
//    private Integer frequency;
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