package com.whoai.blog.sso.web.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户注册（基础注册，只需要提供用户名和密码即可，后续基本信息可在详情页进行补充）
 * <p>1.客户端注册的都是普通用户
 * <p>2.管理端注册的为博客作者，可以管理自有文章的权限，如：限制用户下载等
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户注册")
public class RegisterParam extends BaseApplicationTypeParam {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}
