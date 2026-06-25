package com.coditas.cohorttheplatform.dto.coursebatch.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCourseMaterialRequestDto implements Serializable {

    private MultipartFile file;

}
