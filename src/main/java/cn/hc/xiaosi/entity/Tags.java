package cn.hc.xiaosi.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_tags
 *
 * @author
 */
@Data
public class Tags implements Serializable {
    /**
     * 标签标识
     */
    private String entag;

    /**
     * 类别标识
     */
    private String encategory;

    /**
     * 名称
     */
    private String caption;

    /**
     * 序号
     */
    private Integer taglevel;

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