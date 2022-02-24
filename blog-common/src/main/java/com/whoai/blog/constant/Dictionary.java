package com.whoai.blog.constant;

import java.io.Serializable;

/**
 * 字典项接口
 *
 * @date 2022/2/23
 */
public interface Dictionary extends Serializable {

    /**
     * 字典项的 key
     *
     * @return key
     */
    Integer getCode();

    /**
     * 字典项的 value
     *
     * @return value
     */
    String getValue();

}
