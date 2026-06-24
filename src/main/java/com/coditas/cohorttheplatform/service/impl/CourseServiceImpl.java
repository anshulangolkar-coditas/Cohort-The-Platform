package com.coditas.cohorttheplatform.service.impl;

import com.coditas.cohorttheplatform.repository.CourseRepository;
import com.coditas.cohorttheplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


}
