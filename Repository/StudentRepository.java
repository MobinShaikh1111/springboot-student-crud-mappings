package com.LearnMapping.LearnMapping.Repository;

import com.LearnMapping.LearnMapping.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
