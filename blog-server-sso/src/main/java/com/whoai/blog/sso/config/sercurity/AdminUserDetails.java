package com.whoai.blog.sso.config.sercurity;

import com.whoai.blog.constant.Permission;
import com.whoai.blog.constant.Role;
import com.whoai.blog.constant.Status;
import com.whoai.blog.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 安全认证的用户详情
 *
 * @since 2022/4/12
 */
public class AdminUserDetails implements UserDetails {
    private static final long serialVersionUID = -3686913339520700837L;

    private Admin admin;
    private Role role;
    private List<Permission> permissionList;

    public AdminUserDetails() {
    }

    public AdminUserDetails(Admin admin, Role role, List<Permission> permissionList) {
        this.admin = admin;
        this.role = role;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities = permissionList.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return admin.getStatus() == Status.VALID;
    }
}
