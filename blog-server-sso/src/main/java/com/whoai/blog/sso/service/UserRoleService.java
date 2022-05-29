package com.whoai.blog.sso.service;

import com.whoai.blog.sso.web.vo.UserRoleVO;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author xiaosi
 * @date 2022/5/28
 * @since 1.0
 */
public interface UserRoleService {

    /**
     * 获取生效的已有角色
     *
     * @return 已有角色列表
     */
    List<UserRoleVO> listAllUserRole();

}
