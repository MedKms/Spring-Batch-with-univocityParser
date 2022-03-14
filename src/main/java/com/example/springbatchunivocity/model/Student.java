package com.example.springbatchunivocity.model;

import com.univocity.parsers.annotations.Parsed;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Student {
    @Parsed(field = "student_id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Parsed(field = "student_lastName")
    private String lastName;
    @Parsed(field = "student_firstName")
    private String firstName;
    @Parsed(field = "student_email")
    private String email;
    @Parsed(field = "student_age")
    private double age;
}
