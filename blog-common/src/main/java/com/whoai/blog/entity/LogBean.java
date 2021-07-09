package com.whoai.blog.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_log
 * @author 
 */
@Accessors(chain = true)
@Data
public class LogBean implements Serializable {
    private static final long serialVersionUID = -4448167276671780018L;
    /**
     * 日志编号
     */
    private Integer logno;

    /**
     * 操作的管理员编号
     */
    private String operator;

    /**
     * 操作类型
     */
    private String operation;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createtime;

}