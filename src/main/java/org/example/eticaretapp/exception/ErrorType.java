package org.example.eticaretapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_SERVER_ERROR(500,"Sunucuda beklenmeyen bir hata oldu. Lütfen tekrar deneyin",HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyin.", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(5001,"Girilen şifreler birbiri ile uyuşmamaktadır.",HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_OR_PASSWORD(5002, "Kullanıcı adı ya da şifre hatalıdır.", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(5003, "Geçersiz token bilgisi", HttpStatus.BAD_REQUEST),
    NOTFOUND_USER(6000,"Kullanıcı bulunamadı",HttpStatus.NOT_FOUND),
    NOTFOUND_PRODUCT(7001, "girilen id ile eslesen urun bulunamadi", HttpStatus.BAD_REQUEST),
    SELLER_PRODUCT_ERROR(7002, "girilen urun, girilen satici tarafindan satilmiyor", HttpStatus.BAD_REQUEST),
    USERNAME_AND_EMAIL_ERROR(5004,"Kullanıcı adı ya da mail adresi daha önce kaydedilmiş. Lütfen tekrar deneyiniz...", HttpStatus.BAD_REQUEST)
    ,ACCESS_DENIED(6006, "admin olarak kayit yapamazsiniz!", HttpStatus.BAD_REQUEST)
    ;
    int code;
    String message;
    HttpStatus httpStatus;
}