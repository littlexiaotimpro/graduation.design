package com.whoai.blog.sso.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whoai.blog.entity.UserRole;
import com.whoai.blog.sso.mapper.UserRoleMapper;
import com.whoai.blog.sso.service.UserRoleService;
import com.whoai.blog.sso.web.vo.UserRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色服务接口实现
 *
 * @author xiaosi
 * @date 2022/5/28
 * @since 1.0
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<UserRoleVO> listAllUserRole() {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getActive, 1);
        List<UserRole> userRoles = this.baseMapper.selectList(queryWrapper);
        return userRoles.stream()
                .map(userRole -> {
                    UserRoleVO userRoleVO = new UserRoleVO();
                    userRoleVO.setId(userRole.getId());
                    userRoleVO.setCode(userRole.getCode());
                    userRoleVO.setName(userRole.getName());
                    userRoleVO.setCreateUser(userRole.getCreateUser());
                    userRoleVO.setGmtCreated(userRole.getGmtCreated());
                    userRoleVO.setModifyUser(userRole.getModifyUser());
                    userRoleVO.setGmtModify(userRole.getGmtModify());
                    return userRoleVO;
                }).collect(Collectors.toList());
    }
}
