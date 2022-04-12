package com.whoai.blog.constant;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义用户权限
 * <tr>若当前用户角色为管理员，初始值为所有权限
 * <tr>若当前用户角色为普通用户，则包含对自己创建的资源的所有权限
 * <tr>若当前用户为游客，仅限浏览及阅读
 */
@ToString
public enum Permission implements Dictionary {
    READ(10, "默认权限，系统所有公开资源的读取，包含以下的所有权限"),
    READ_ARTICLE(11, "游客权限，可以阅读文章【无法下载文章对应文件】"),

    CREATE(20, "默认权限，新增资源，包含以下的所有权限"),
    CREATE_USER(21, "管理员权限，新增用户"),
    CREATE_ARTICLE_FILE(22, "新增文章，并上传相应文件【文章文件，文章图片】"),
    CREATE_RECORD(23, "新增评论"),
    ONLY_FILE_UPLOAD(24, "仅上传文件"),

    UPDATE(30, "默认权限，更新资源内容，包含以下的所有权限"),
    UPDATE_USER(31, "更新用户信息"),
    UPDATE_ARTICLE_FILE(32, "更新文章信息，并更新相应文件【文章文件，文章图片】"),
    UPDATE_FILE(33, "仅更新文件内容"),

    DELETE(40, "默认权限，删除资源内容，包含以下的所有权限"),
    DELETE_USER(41, "管理员权限，删除用户【普通用户默认必须有销户权限】"),
    DELETE_RECORD(42, "删除评论【评论无更新操作】"),
    DELETE_FILE(42, "仅删除文件"),
    ;

    private final Integer code;
    private final String value;

    Permission(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static List<Permission> converts(String codes) {
        List<Permission> permissions = new ArrayList<>();
        if (codes == null) {
            return permissions;
        }
        String[] split = codes.split(",");
        Permission[] values = Permission.values();
        for (String code : split) {
            for (Permission value : values) {
                if (code.equals(value.getCode().toString())) {
                    permissions.add(value);
                    break;
                }
            }
        }
        return permissions;
    }

    public static Permission convert(String code) {
        Permission[] values = Permission.values();
        for (Permission value : values) {
            if (code != null && code.equals(value.getCode().toString())) {
                return value;
            }
        }
        return null;
    }
}
