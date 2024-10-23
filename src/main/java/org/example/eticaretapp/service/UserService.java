package org.example.eticaretapp.service;

import org.example.eticaretapp.entity.Mail;
import org.example.eticaretapp.entity.User;
import org.example.eticaretapp.repository.AuthRepository;
import org.example.eticaretapp.repository.UserRepository;
import org.example.eticaretapp.utility.JwtManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtManager jwtManager;
    private final MailService mailService;


    public UserService(UserRepository userRepository, JwtManager jwtManager, AuthRepository loginRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.jwtManager = jwtManager;
        this.mailService = mailService;
    }


    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public void verifyAccount(String activationCode ){
        mailService.findAll();
        for(Mail mail : mailService.findAll()){
            if(activationCode.equals(mail.getActivationCode())){
                User user = userRepository.findById(mail.getAuthId()).get();
                user.setIsVerified(true);
                userRepository.save(user);
            }

        }
    }
}
