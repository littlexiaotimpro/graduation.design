package com.whoai.blog.sso.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whoai.blog.entity.User;
import com.whoai.blog.entity.UserInfo;
import com.whoai.blog.sso.UserLoginInfo;
import com.whoai.blog.sso.mapper.UserInfoMapper;
import com.whoai.blog.sso.mapper.UserMapper;
import com.whoai.blog.sso.service.UserService;
import com.whoai.blog.sso.web.vo.UserDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户操作服务接口实现
 *
 * @author xiaosi
 * @date 2022/5/19
 * @since 1.0
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserDetailVO> listUserDetails() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getActive, 1);
        List<User> users = this.baseMapper.selectList(queryWrapper);
        List<UserDetailVO> list = new ArrayList<>();
        for (User user : users) {
            UserInfo userInfo = userInfoMapper.findByUserId(user.getId());
            list.add(UserDetailVO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .nickname(userInfo.getNickname())
                    .gender(userInfo.getGender())
                    .age(userInfo.getAge())
                    .email(userInfo.getEmail())
                    .phone(userInfo.getPhone())
                    .build());
        }
        return list;
    }

    @Override
    public UserLoginInfo findByUsername(String username) {
        User user = this.baseMapper.findByUsername(username);
        return UserLoginInfo.builder()
                .id(user.getId())
                .username(username)
                .build();
    }
}
