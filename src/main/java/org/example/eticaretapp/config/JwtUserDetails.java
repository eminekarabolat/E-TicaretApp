package org.example.eticaretapp.config;


import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.entity.Auth;
import org.example.eticaretapp.entity.User;
import org.example.eticaretapp.entity.UserRole;
import org.example.eticaretapp.service.AuthService;
import org.example.eticaretapp.service.UserRoleService;
import org.example.eticaretapp.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserById(Long userId){

        Optional<Auth> authOptional = authService.findById(userId);
        if (authOptional.isEmpty()) return null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> userRoles = userRoleService.getAllUserRoleByUserId(userId);
        userRoles.forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.getRole())));

        return org.springframework.security.core.userdetails.User.builder()
                .username(authOptional.get().getUsername())
                .password(authOptional.get().getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .authorities(authorities)
                .build();
    }
}