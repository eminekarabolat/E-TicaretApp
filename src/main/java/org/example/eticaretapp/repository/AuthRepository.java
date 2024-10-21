package org.example.eticaretapp.repository;

import org.example.eticaretapp.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);
}
