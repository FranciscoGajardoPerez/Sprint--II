package com.example.be_java_hisp_w19_g2.handlers;

public class UserNotFoundException extends RuntimeException{

  public UserNotFoundException(String message) {
    super(message);
  }
}