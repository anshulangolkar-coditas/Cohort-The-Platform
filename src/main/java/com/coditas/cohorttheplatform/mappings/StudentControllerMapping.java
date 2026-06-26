package com.coditas.cohorttheplatform.mappings;

import com.coditas.cohorttheplatform.dto.student.response.AssignmentSubmitResponseDto;
import com.coditas.cohorttheplatform.dto.student.response.CourseMaterialResponseDto;
import com.coditas.cohorttheplatform.entity.CourseMaterial;
import com.coditas.cohorttheplatform.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class StudentControllerMapping {

    public CourseMaterialResponseDto courseMaterialResponse(CourseMaterial material){
        return CourseMaterialResponseDto.builder()
                .materialId(material.getMaterialId())
                .fileName(material.getFileName())
                .uploadedAt(material.getUploadedOn())
                .build();
    }

    public Page<CourseMaterialResponseDto> getAllMaterials(Page<CourseMaterial> materials){
        return materials.map(this::courseMaterialResponse);
    }

    public AssignmentSubmitResponseDto assignmentSubmitResponse(Submission submission){
        return AssignmentSubmitResponseDto.builder()
                .submissionId(submission.getSubmissionId())
                .fileName(submission.getFileName())
                .submittedOn(submission.getSubmittedAt())
                .build();
    }


}
