package com.LearnMapping.LearnMapping.DTO;


import lombok.Data;
import java.util.List;

@Data
public class StudentResponseDTO {

    private Long studentId;
    private String name;
    private String email;

    private String departmentName;
    private List<String> courses; // Only course names
}

