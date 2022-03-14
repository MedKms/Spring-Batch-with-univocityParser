package com.example.springbatchunivocity.services.readFilesService;

import com.example.springbatchunivocity.model.Student;
import org.springframework.batch.item.ItemReader;

public interface Reader {
    ItemReader<Student> readFile(String path);
}
