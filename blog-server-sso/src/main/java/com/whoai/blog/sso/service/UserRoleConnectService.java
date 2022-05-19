package com.whoai.blog.sso.service;

import com.whoai.blog.sso.web.param.UserInfoSaveParam;

/**
 * 用户角色关联操作服务接口
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
public interface UserRoleConnectService {

    void initUserRole(Long userId);

}
