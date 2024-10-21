package org.example.eticaretapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.eticaretapp.entity.enums.Role;

import java.time.LocalDate;

public record RegisterRequestDto(
        @NotNull
        String name,
        @NotNull
        String surname,
        @Email
        String email,
        @NotNull
        String username,
        @NotBlank
        @Pattern(message = "Lütfen şifre kurallarına uyunuz",
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$")
        String password,
        @NotNull
        String rePassword,
        String phone,
        String address,
        LocalDate birthdate,
        @NotNull
        Role userRole
) {
}
