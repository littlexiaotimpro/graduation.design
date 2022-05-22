package com.whoai.blog.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whoai.blog.entity.UserRoleConnect;
import com.whoai.blog.enums.ApplicationTypeEnum;
import com.whoai.blog.enums.RoleEnum;
import com.whoai.blog.sso.exception.UserManagerException;
import com.whoai.blog.sso.mapper.UserRoleConnectMapper;
import com.whoai.blog.sso.service.UserRoleConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户角色关联操作服务接口实现
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
@Slf4j
@Service
public class UserRoleConnectServiceImpl extends ServiceImpl<UserRoleConnectMapper, UserRoleConnect>
        implements UserRoleConnectService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initUserRole(Long userId, ApplicationTypeEnum type) {
        UserRoleConnect userRoleConnect = new UserRoleConnect();
        userRoleConnect.setUserId(userId);
        boolean flag = type == ApplicationTypeEnum.PROVIDER;
        /*
         * 管理端注册的为作者（亦是用户），客户端注册的为普通用户
         * 若普通用户需要成为作者，则在使用账户登录管理端时
         */
        userRoleConnect.setRole(flag ? RoleEnum.AUTHOR : RoleEnum.NORMAL);
        if (!super.saveOrUpdate(userRoleConnect)) {
            throw new UserManagerException("用户初始化角色失败");
        }
    }
}
