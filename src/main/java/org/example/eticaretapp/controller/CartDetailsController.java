package org.example.eticaretapp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.eticaretapp.constants.RestApis.CART_DETAILS;
import static org.example.eticaretapp.constants.RestApis.SHOPPING_CART;

@RestController
@RequiredArgsConstructor
@RequestMapping(CART_DETAILS)
public class CartDetailsController {
}
