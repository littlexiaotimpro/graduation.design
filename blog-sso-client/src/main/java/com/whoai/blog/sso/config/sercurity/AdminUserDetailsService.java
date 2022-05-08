package com.whoai.blog.sso.config.sercurity;

import com.whoai.blog.constant.Permission;
import com.whoai.blog.constant.Role;
import com.whoai.blog.entity.Admin;
import com.whoai.blog.sso.dao.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * 自定义用户详情服务
 *
 * @since 2022/4/12
 */
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 依据用户名获取用户 TODO 若后续加入缓存功能，此处需要新增逻辑，是否从缓存中获取
        Admin admin = loginMapper.findAdminByAccount(username);
        if (admin != null) {
            Role role = admin.getRole();
            List<Permission> permissionList = admin.getPermission();
            return new AdminUserDetails(admin, role, permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
