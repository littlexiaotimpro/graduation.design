package com.whoai.blog.sso.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 获取角色列表
 *
 * @author xiaosi
 * @date 2022/5/28
 * @since 1.0
 */
@Data
@ApiModel("用户角色模型")
public class UserRoleVO {

    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty("角色标识")
    private String code;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建时间")
    private Date gmtCreated;

    @ApiModelProperty("修改人")
    private String modifyUser;

    @ApiModelProperty("修改时间")
    private Date gmtModify;

}
