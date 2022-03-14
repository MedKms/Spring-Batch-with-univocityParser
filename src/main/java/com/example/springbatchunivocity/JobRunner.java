package com.example.springbatchunivocity;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {

    private final JobLauncher jobLauncher;
    private final Job job;

    public JobRunner(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }
    @Bean
    public void run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters=new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(job,jobParameters);
    }
}
