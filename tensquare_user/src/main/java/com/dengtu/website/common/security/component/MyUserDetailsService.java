package com.dengtu.website.common.security.component;

import com.dengtu.website.domain.User;
import com.dengtu.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

//    @Autowired
//    private SysRoleService roleService;

//    @Autowired
//    private SysUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        User user = userService.selectOneByPhone(username);

        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("手机号不存在");
        }

        // 添加权限
//        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
//        for (SysUserRole userRole : userRoles) {
//            SysRole role = roleService.selectById(userRole.getRoleId());
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
        authorities.add(new SimpleGrantedAuthority("ROLE_ROLE"));
        // 返回UserDetails实现类
        return new SecurityUser(user.getMobile(), user.getPassword(), authorities, user.getStatus() == 1 ? Boolean.TRUE : Boolean.FALSE, user);
    }
}