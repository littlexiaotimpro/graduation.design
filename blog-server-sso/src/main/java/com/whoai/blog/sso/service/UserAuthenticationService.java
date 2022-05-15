package com.whoai.blog.sso.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.whoai.blog.entity.User;
import com.whoai.blog.entity.UserInfo;
import com.whoai.blog.entity.UserPermission;
import com.whoai.blog.entity.UserRole;
import com.whoai.blog.sso.mapper.UserInfoMapper;
import com.whoai.blog.sso.mapper.UserMapper;
import com.whoai.blog.sso.mapper.UserPermissionMapper;
import com.whoai.blog.sso.mapper.UserRoleMapper;
import com.whoai.blog.sso.web.po.UserAuthenticationPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户授权服务
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
@Slf4j
@Service
public class UserAuthenticationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserPermissionMapper userPermissionMapper;


    public UserAuthenticationPO createUserAuthenticationPO(String username) {
        User user = userMapper.findByUsername(username);
        UserInfo userInfo = userInfoMapper.findByUserId(user.getId());
        List<UserRole> userRoles = userRoleMapper.findByUserId(user.getId());
        LambdaQueryWrapper<UserPermission> wrapper = new LambdaQueryWrapper<>();
        List<Long> roleIds = userRoles.stream().map(UserRole::getId).collect(Collectors.toList());
        wrapper.in(UserPermission::getId, roleIds)
                .eq(UserPermission::getActive, 1);
        List<UserPermission> userPermissions = userPermissionMapper.selectList(wrapper);
        log.info("selectList: {}", userPermissions);
        List<UserPermission> byRoleIds = userPermissionMapper.findByRoleIds(roleIds);
        log.info("findByRoleIds: {}", byRoleIds);
        return UserAuthenticationPO.builder()
                .user(user)
                .userInfo(userInfo)
                .userRoles(userRoles)
                .userPermissions(userPermissions)
                .build();
    }

}
