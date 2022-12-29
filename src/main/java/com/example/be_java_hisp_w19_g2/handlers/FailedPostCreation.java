package com.example.be_java_hisp_w19_g2.handlers;

public class FailedPostCreation extends RuntimeException {

    public FailedPostCreation(String message) {
      super(message);
    }

}
