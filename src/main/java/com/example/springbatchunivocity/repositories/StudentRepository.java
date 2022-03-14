package com.example.springbatchunivocity.repositories;

import com.example.springbatchunivocity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
