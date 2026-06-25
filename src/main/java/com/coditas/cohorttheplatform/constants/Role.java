package com.coditas.cohorttheplatform.constants;

import java.util.List;

public enum Role {

    ADMIN,
    INSTRUCTOR,
    STUDENT;


    public static List<Role> toValue(List<String> roles){

        return roles.stream()
                .map(String::toUpperCase)
                .map(Role::valueOf)
                .toList();
    }

}
