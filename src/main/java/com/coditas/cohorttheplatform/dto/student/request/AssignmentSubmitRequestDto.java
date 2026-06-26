package com.coditas.cohorttheplatform.dto.student.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentSubmitRequestDto {

    private MultipartFile file;

}
