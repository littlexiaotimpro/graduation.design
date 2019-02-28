package cn.hc.xiaosi.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_admin
 * @author 
 */
@Data
public class Admin implements Serializable {
    /**
     * 管理员编号
     */
    private String adminid;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限
     */
    private Integer permission;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;

    private static final long serialVersionUID = 1L;
}