package org.example.eticaretapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import static org.example.eticaretapp.constants.RestApis.*;

import org.example.eticaretapp.dto.request.AuthRequestDto;
import org.example.eticaretapp.dto.request.RegisterRequestDto;
import org.example.eticaretapp.dto.response.BaseResponse;
import org.example.eticaretapp.entity.User;
import org.example.eticaretapp.service.AuthService;
import org.example.eticaretapp.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService registerService;
    private final MailService mailService;


    @PostMapping(REGISTER)
    public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid RegisterRequestDto dto){
        registerService.register(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                        .success(true)
                        .message("Kayıt işlemi başarılı")
                        .code(200)
                        .data(true)
                .build());
    }

    @PostMapping(LOGIN)
    public ResponseEntity<BaseResponse<String>> login(@RequestBody @Valid AuthRequestDto loginRequestDto) {
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .success(true)
                .message("Giriş başarılı...")
                .code(200)
                .data(registerService.login(loginRequestDto))
                .build());
    }

    @PostMapping("/test")
    public String sendMailTest(String mail){
        User user = new User();
        mailService.sendVerificationMail(mail);
        return "Mail gönderdik.";
    }

    
}