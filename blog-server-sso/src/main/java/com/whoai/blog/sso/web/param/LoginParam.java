package com.whoai.blog.sso.web.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录参数
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
@Data
@ApiModel("登录请求参数")
public class LoginParam {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}
