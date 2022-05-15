package com.whoai.blog.sso.web.param;

import com.whoai.blog.enums.ApplicationTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
@Data
@ApiModel("应用类型请求参数")
public class BaseApplicationTypeParam {

    @ApiModelProperty("应用类型")
    private ApplicationTypeEnum type;
}
