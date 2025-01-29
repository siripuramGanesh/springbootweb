package com.ganeshsiripuram.springbootweb.springbootweb.exceptions;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
