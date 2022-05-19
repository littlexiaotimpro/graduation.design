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

    void initUserInfo(Long userId);

    void saveUserInfo(UserInfoSaveParam param);

}
