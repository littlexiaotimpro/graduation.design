package com.whoai.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.whoai.blog.enums.GenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户基本信息
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("u_user_info")
public class UserInfo extends BaseModifyEntity {

    private static final long serialVersionUID = -2021003111308524424L;
    /**
     * 关联的用户id
     */
    private Long userId;

    /**
     * 用户昵称，用于个人信息的展示，用户注册时若不提供，则自动随机生成
     */
    private String nickname;

    /**
     * 性别
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private GenderEnum gender;

    /**
     * 年龄
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer age;

    /**
     * 邮箱
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String email;

    /**
     * 联系方式
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String phone;

}
