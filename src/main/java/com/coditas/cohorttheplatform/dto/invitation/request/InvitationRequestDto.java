package com.coditas.cohorttheplatform.dto.invitation.request;

import com.coditas.cohorttheplatform.exception.ValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvitationRequestDto {

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    private String fullName;

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    @Email(message = ValidationMessages.VALID_EMAIL)
    private String email;

    @NotBlank(message = ValidationMessages.FIELD_NOT_BLANK)
    private String role;

}
