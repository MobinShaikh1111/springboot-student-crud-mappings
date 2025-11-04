package com.LearnMapping.LearnMapping.Service;


import com.LearnMapping.LearnMapping.DTO.StudentRequestDTO;
import com.LearnMapping.LearnMapping.DTO.StudentResponseDTO;
import com.LearnMapping.LearnMapping.Repository.CourseRepository;
import com.LearnMapping.LearnMapping.Repository.DepartmentRepository;
import com.LearnMapping.LearnMapping.Repository.StudentRepository;
import com.LearnMapping.LearnMapping.entity.Course;
import com.LearnMapping.LearnMapping.entity.Department;
import com.LearnMapping.LearnMapping.entity.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
       /*Student student= modelMapper.map(dto,Student.class);
        if (student.getCourse() != null) {
            student.getCourse().forEach(course -> course.setStudent(student));
        }//forEach(course -> course.setStudent(student));
        */
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        //  Set Department
        /*
        Department dept = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        student.setDepartment(dept);
        */

        Department dept = departmentRepository.findByName(dto.getDepartment().getName())
                .orElseGet(() -> {
                    Department d = new Department();
                    d.setName(dto.getDepartment().getName());
                    return departmentRepository.save(d);
                });
        student.setDepartment(dept);
        //  Handle Courses
        List<Course> courseList = dto.getCourses().stream().map(cDto ->
                courseRepository.findByName(cDto.getName())
                        .orElseGet(() -> {
                            Course newCourse = new Course();
                            newCourse.setName(cDto.getName());
                            newCourse.setDepartment(dept);
                            return courseRepository.save(newCourse);
                        })
        ).toList();

        student.setCourses(courseList);
        courseList.forEach(course -> course.getStudents().add(student));  //  add student to course


        /*// Set Courses
        List<Course> courseList = new ArrayList<>();
        dto.getCourseIds().forEach(courseId -> {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            courseList.add(course);
        });
        student.setCourses(courseList);
        */


        // save student
        Student savedStudent = studentRepository.save(student);

        // Convert to response DTO
        return modelMapper.map(savedStudent, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(std -> modelMapper.map(std, StudentResponseDTO.class))
                .toList();
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        if (dto.getDepartment() != null && dto.getDepartment().getName() != null) {
            Department dept = departmentRepository.findByName(dto.getDepartment().getName())
                    .orElseGet(() -> {
                        Department d = new Department();
                        d.setName(dto.getDepartment().getName());
                        return departmentRepository.save(d);
                    });
            existing.setDepartment(dept);
        }

        // Update Courses
        if (dto.getCourses() != null) {
            List<Course> updatedCourses = new ArrayList<>();

            dto.getCourses().forEach(cDto -> {
                Course course = courseRepository.findByName(cDto.getName())
                        .orElseGet(() -> {
                            Course newCourse = new Course();
                            newCourse.setName(cDto.getName());
                            newCourse.setDepartment(existing.getDepartment());
                            return courseRepository.save(newCourse);
                        });

                updatedCourses.add(course);
            });

            existing.getCourses().clear(); // remove old relations
            existing.getCourses().addAll(updatedCourses);
            updatedCourses.forEach(course -> course.getStudents().add(existing));
        }

            // Map incoming changes on existing object
            modelMapper.map(dto, existing);

            Student updated = studentRepository.save(existing);
            return modelMapper.map(updated, StudentResponseDTO.class);
        }

    @Override
    public String deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }

}