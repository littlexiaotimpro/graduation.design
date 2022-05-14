package com.whoai.blog.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 权限
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
public enum PermissionEnum {

    /**
     * 所有权限
     */
    ALL(1, "ALL"),

    /**
     * 查询
     */
    READ(2, "READ"),

    /**
     * 新增
     */
    CREATE(3, "CREATE"),

    /**
     * 删除
     */
    DELETE(4, "DELETE"),

    /**
     * 修改
     */
    MODIFY(5, "MODIFY"),

    /**
     * 文件上传
     */
    UPLOAD(6, "UPLOAD"),

    /**
     * 文件下载
     */
    DOWNLOAD(7, "DOWNLOAD");

    @EnumValue
    private final Integer id;

    @JsonValue
    private final String code;

    PermissionEnum(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
