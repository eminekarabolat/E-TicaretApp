package org.example.eticaretapp.service;

import jakarta.validation.Valid;
import org.example.eticaretapp.dto.request.AuthRequestDto;
import org.example.eticaretapp.dto.request.RegisterRequestDto;
import org.example.eticaretapp.entity.Auth;
import org.example.eticaretapp.entity.User;
import org.example.eticaretapp.entity.enums.Role;
import org.example.eticaretapp.exception.ETicaretException;
import org.example.eticaretapp.exception.ErrorType;
import org.example.eticaretapp.mapper.UserMapper;
import org.example.eticaretapp.repository.AuthRepository;
import org.example.eticaretapp.repository.UserRepository;
import org.example.eticaretapp.utility.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AuthService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EncryptionService encryptionService;
    
    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private MailService mailService;

    public void register(@Valid RegisterRequestDto dto){
        if (dto.userRole().equals(Role.ADMIN)) throw new ETicaretException(ErrorType.ACCESS_DENIED);
        Auth login = Auth.builder()
                .username(dto.username())
                .password(encryptionService.encryptPassword(dto.password()))
                .role(dto.userRole())
                .build();
        authRepository.save(login);

        User user = UserMapper.INSTANCE.fromRegisterRequestDto(dto);
        user.setAuthId(login.getId());
        userRepository.save(user);
    }

    public String login(@Valid AuthRequestDto authRequestDto) {
        Optional<Auth> optionalAuthUser = authRepository.findOptionalByUsername(authRequestDto.username());
        if(optionalAuthUser.isEmpty() ||
                !encryptionService.checkPassword(
                        authRequestDto.password(), optionalAuthUser.get().getPassword()))
            throw new ETicaretException(ErrorType.INVALID_USERNAME_OR_PASSWORD);



        Optional<User> optionalUser = userRepository.findOptionalByAuthId(optionalAuthUser.get().getId());

        if(!optionalUser.get().getIsVerified()){
            mailService.sendVerificationMail(optionalUser.get());
            throw new ETicaretException(ErrorType.USER_NOT_VERIFIED);
        }
        String token = jwtManager.createToken(optionalUser.get().getId());
        return token;
    }
    
    public Optional<Auth> findById(Long id){
        return authRepository.findById(id);
    }


}