package org.example.eticaretapp.service;


import lombok.RequiredArgsConstructor;
import org.example.eticaretapp.entity.UserRole;
import org.example.eticaretapp.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public void saveUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    public List<UserRole> getAllUserRoleByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }
}