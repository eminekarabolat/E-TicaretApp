package org.example.eticaretapp.controller;

import lombok.RequiredArgsConstructor;
import static org.example.eticaretapp.constants.RestApis.*;
import org.example.eticaretapp.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


}
