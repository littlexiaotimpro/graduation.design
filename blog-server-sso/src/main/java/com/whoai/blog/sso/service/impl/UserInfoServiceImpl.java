package com.whoai.blog.sso.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whoai.blog.entity.UserInfo;
import com.whoai.blog.sso.exception.UserManagerException;
import com.whoai.blog.sso.mapper.UserInfoMapper;
import com.whoai.blog.sso.service.UserInfoService;
import com.whoai.blog.sso.web.param.UserInfoSaveParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * 用户详情操作服务接口实现
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initUserInfo(Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setNickname(UUID.randomUUID().toString());
        if (!super.save(userInfo)) {
            throw new UserManagerException("初始化用户基本信息失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserInfo(UserInfoSaveParam param) {
        UserInfo userInfo = this.baseMapper.findByUserId(param.getId());
        userInfo = Optional.ofNullable(userInfo).orElse(new UserInfo());
        userInfo.setUserId(param.getId());
        userInfo.setNickname(param.getNickname());
        userInfo.setAge(param.getAge());
        userInfo.setEmail(param.getEmail());
        userInfo.setGender(param.getGender());
        userInfo.setPhone(param.getPhone());
        if (!super.saveOrUpdate(userInfo)) {
            throw new UserManagerException("用户基本信息保存失败");
        }
    }
}
