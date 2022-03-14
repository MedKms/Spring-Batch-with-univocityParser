package com.example.springbatchunivocity.config;

import com.example.springbatchunivocity.model.Student;

import com.example.springbatchunivocity.repositories.StudentRepository;
import com.example.springbatchunivocity.services.readFilesService.Reader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@EnableBatchProcessing
@Configuration
public class JobConfig {
    private final StudentRepository studentRepository;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private Reader reader;

    @Value("${filePath}")
    private String path;


    public JobConfig(StudentRepository studentRepository, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, Reader reader) {
        this.studentRepository = studentRepository;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.reader = reader;
    }
    @Bean
    public Job myJob(){
        return jobBuilderFactory.get("MyJob")
                .start(myStep())
                .build();
    }
    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep")
                .<Student,Student>chunk(10)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }
    @Bean
    public ItemReader<Student> itemReader(){
        return  reader.readFile(path);
    }
    @Bean
    public ItemWriter<Student> itemWriter(){
        return new ItemWriter<Student>() {
            @Override
            public void write(List<? extends Student> list) throws Exception {
                for (Student student:list){
                    System.out.println(student);
                }
                studentRepository.saveAll(list);
            }
        };
    }
}
