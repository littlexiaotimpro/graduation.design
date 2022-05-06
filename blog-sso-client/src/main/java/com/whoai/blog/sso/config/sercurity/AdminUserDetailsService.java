package com.whoai.blog.sso.config.sercurity;

import com.whoai.blog.constant.Permission;
import com.whoai.blog.constant.Role;
import com.whoai.blog.dto.AdminLoginDTO;
import com.whoai.blog.entity.Admin;
import com.whoai.blog.service.AdminService;
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
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 依据用户名获取用户
        AdminLoginDTO adminLoginDTO = new AdminLoginDTO();
        adminLoginDTO.setAccount(username);
        Admin admin = adminService.findUserByAccount(adminLoginDTO);
        if (admin != null) {
            Role role = admin.getRole();
            List<Permission> permissionList = admin.getPermission();
            return new AdminUserDetails(admin, role, permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
