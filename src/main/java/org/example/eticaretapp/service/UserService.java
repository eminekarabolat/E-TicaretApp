package org.example.eticaretapp.service;

import org.example.eticaretapp.repository.AuthRepository;
import org.example.eticaretapp.repository.UserRepository;
import org.example.eticaretapp.utility.JwtManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtManager jwtManager;


    public UserService(UserRepository userRepository, JwtManager jwtManager, AuthRepository loginRepository) {
        this.userRepository = userRepository;
        this.jwtManager = jwtManager;

    }



}
