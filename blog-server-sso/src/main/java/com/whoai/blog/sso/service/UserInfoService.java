package com.whoai.blog.sso.service;

import com.whoai.blog.sso.web.param.UserInfoSaveParam;

/**
 * 用户详情操作服务接口
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
public interface UserInfoService {

    /**
     * 用户注册时，初始化基本信息
     *
     * @param userId 用户ID
     */
    void initUserInfo(Long userId);

    /**
     * 新增/编辑用户基本信息
     *
     * @param param 用户基本信息
     */
    void saveOrUpdateUserInfo(UserInfoSaveParam param);

    /**
     * 用户注销账号后，基本信息同步删除
     *
     * @param userId 用户ID
     */
    void deleteUserInfo(Long userId);

}
