package org.example.eticaretapp.controller;

import lombok.RequiredArgsConstructor;
import static org.example.eticaretapp.constants.RestApis.*;

import org.example.eticaretapp.dto.response.BaseResponse;
import org.example.eticaretapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(VERIFYACCOUNT)
    public ResponseEntity<BaseResponse<Boolean>> verifyAccount(String activationCode){
        userService.verifyAccount(activationCode);
        return ResponseEntity.ok(BaseResponse.getSuccess(true, "Hesap aktif"));
    }

}