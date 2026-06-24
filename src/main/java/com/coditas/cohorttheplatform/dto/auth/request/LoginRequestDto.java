package com.coditas.cohorttheplatform.dto.auth.request;

import com.coditas.cohorttheplatform.exception.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
    @Email(message = ValidationMessages.VALID_EMAIL)
    private String email;

    @NotBlank(message = ValidationMessages.PASSWORD_REQUIRED)
    private String password;

}
