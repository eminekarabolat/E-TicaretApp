package org.example.eticaretapp.repository;


import org.example.eticaretapp.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    List<UserRole> findByUserId(Long userId);
}