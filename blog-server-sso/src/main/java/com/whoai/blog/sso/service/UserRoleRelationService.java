package com.whoai.blog.sso.service;

import com.whoai.blog.enums.ApplicationTypeEnum;

/**
 * 用户角色关联操作服务接口
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
public interface UserRoleRelationService {

    /**
     * 用户注册时初始化角色
     *
     * @param userId 用户ID
     * @param type   应用类型：PROVIDER/CONSUMER
     */
    void initUserRole(Long userId, ApplicationTypeEnum type);

}
