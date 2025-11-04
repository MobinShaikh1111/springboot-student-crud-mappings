package com.LearnMapping.LearnMapping.Service;

import com.LearnMapping.LearnMapping.DTO.StudentRequestDTO;
import com.LearnMapping.LearnMapping.DTO.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDto);
    StudentResponseDTO getStudentById(Long id);
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDto);
    String deleteStudent(Long id);

}
