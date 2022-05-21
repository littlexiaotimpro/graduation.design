package com.whoai.blog.sso.sercurity.bean;

import com.whoai.blog.entity.UserPermission;
import com.whoai.blog.entity.UserRole;
import com.whoai.blog.sso.web.po.UserAuthenticationPO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户详情
 *
 * @author xiaosi
 * @date 2022/5/14
 * @since 1.0
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 3857840057778648732L;

    private final UserAuthenticationPO userAuthenticationPO;

    public UserDetailsImpl(UserAuthenticationPO userAuthenticationPO) {
        this.userAuthenticationPO = userAuthenticationPO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRole> userRoles = userAuthenticationPO.getUserRoles();
        List<UserPermission> userPermissions = userAuthenticationPO.getUserPermissions();
        Set<SimpleGrantedAuthority> grantedAuthorities = userPermissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getCode()))
                .collect(Collectors.toSet());
        grantedAuthorities.addAll(userRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getCode()))
                .collect(Collectors.toSet()));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return userAuthenticationPO.getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return userAuthenticationPO.getUser().getUsername();
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
        return userAuthenticationPO.getUser().getActive() == 1;
    }
}
