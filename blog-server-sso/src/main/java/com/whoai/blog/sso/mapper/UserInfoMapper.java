package com.whoai.blog.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whoai.blog.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户基本信息
 *
 * @author xiaosi
 * @date 2022/5/15
 * @since 1.0
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 依据用户ID查询用户信息
     *
     * @param userId 用户Id
     * @return userinfo
     */
    UserInfo findByUserId(@Param("userId") Long userId);

}
