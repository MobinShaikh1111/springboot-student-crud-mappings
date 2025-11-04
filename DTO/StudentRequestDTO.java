package com.LearnMapping.LearnMapping.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class StudentRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

//    private Long departmentId;
//    private List<Long> courseIds;
    private DepartmentDTO department;

    private List<CourseDTO> courses;

    @Data
    public static class DepartmentDTO {
        @NotBlank(message = "Department name is required")
        private String name;
    }

    @Data
    public static class CourseDTO {
        @NotBlank(message = "Course name is required")
        private String name;
    }


}

