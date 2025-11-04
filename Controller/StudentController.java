package com.LearnMapping.LearnMapping.Controller;

import com.LearnMapping.LearnMapping.DTO.StudentRequestDTO;
import com.LearnMapping.LearnMapping.DTO.StudentResponseDTO;
import com.LearnMapping.LearnMapping.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@RequestBody StudentRequestDTO dto) {
        return ResponseEntity.ok(studentService.createStudent(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(
            @PathVariable Long id, @RequestBody StudentRequestDTO dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}

