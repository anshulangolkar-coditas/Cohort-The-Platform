package com.coditas.cohorttheplatform.dto.auth.request;

import com.coditas.cohorttheplatform.exception.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegenerateAccessTokenRequest {

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    private String refreshToken;

}
