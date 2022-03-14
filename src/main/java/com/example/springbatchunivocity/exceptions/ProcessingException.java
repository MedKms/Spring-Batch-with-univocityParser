package com.example.springbatchunivocity.exceptions;

public class ProcessingException extends RuntimeException{
    public ProcessingException(String message) {
        super(message);
    }
}
