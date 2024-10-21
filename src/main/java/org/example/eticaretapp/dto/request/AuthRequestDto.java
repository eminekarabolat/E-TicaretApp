package org.example.eticaretapp.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AuthRequestDto(

        @NotNull
        String username,

        @NotNull
        @Pattern(message = "Lütfen şifre kurallarına uyunuz",
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$")
        String password
) {
}
