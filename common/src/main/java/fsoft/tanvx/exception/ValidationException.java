package fsoft.tanvx.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class ValidationException extends RuntimeException{
  private Map<String, List<String>> errors = new HashMap<>();

  private List<String> errorMessages = new ArrayList<>();

  public ValidationException(List<String> errorMessages, String name, String message){
    this.errorMessages = errorMessages;
    this.errors = Collections.singletonMap(name, Collections.singletonList(message));
  }

  public ValidationException(List<String> errorMessages, Map<String, List<String>> errors){
    this.errorMessages = errorMessages;
    this.errors = errors;
  }

  public ValidationException(String errorMessage, String name, String message){
    this.errorMessages = Collections.singletonList(errorMessage);
    this.errors = Collections.singletonMap(name, Collections.singletonList(message));
  }

  public ValidationException(String name, String message){
    this.errors = Collections.singletonMap(name, Collections.singletonList(message));
  }

  public ValidationException(Map<String, List<String >> errors){
    this.errors = errors;
  }
}
