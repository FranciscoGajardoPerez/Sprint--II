package com.example.be_java_hisp_w19_g2.exceptions;

import com.example.be_java_hisp_w19_g2.dtos.MessageDTO;
import com.example.be_java_hisp_w19_g2.handlers.FailedPostCreation;
import com.example.be_java_hisp_w19_g2.handlers.InvalidParamException;
import com.example.be_java_hisp_w19_g2.handlers.UserNotFoundException;
import com.example.be_java_hisp_w19_g2.handlers.UserNotSeller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<MessageDTO> userNotFound(RuntimeException e){
    return ResponseEntity.badRequest().body(
        new MessageDTO(e.getMessage())
    );
  }

  @ExceptionHandler(FailedPostCreation.class)
  public ResponseEntity<MessageDTO> failedPostCreation(RuntimeException e){
    return ResponseEntity.badRequest().body(
        new MessageDTO(e.getMessage())
    );
  }

  @ExceptionHandler(UserNotSeller.class)
  public ResponseEntity<MessageDTO> userNotSeller(RuntimeException e) {
    return ResponseEntity.badRequest().body(
        new MessageDTO(e.getMessage())
    );
  }

  @ExceptionHandler(InvalidParamException.class)
  public ResponseEntity<MessageDTO> invalidParamException (RuntimeException e) {
    return ResponseEntity.badRequest().body(
            new MessageDTO(e.getMessage())
    );
  }
}
