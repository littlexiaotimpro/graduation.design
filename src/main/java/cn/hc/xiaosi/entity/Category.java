package cn.hc.xiaosi.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_category
 *
 * @author
 */
@Data
public class Category implements Serializable {
    /**
     * 类别标识
     */
    private String encategory;

    /**
     * 导航标识
     */
    private String ennav;

    /**
     * 名称
     */
    private String caption;

    /**
     * 序号
     */
    private Integer catelevel;

    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

}