package fsoft.tanvx.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class ServiceException extends RuntimeException{
  private Optional<HttpStatus> httpStatus;

  private List<String> messages;

  public ServiceException(HttpStatus httpStatus){
    this.httpStatus = Optional.ofNullable(httpStatus);
  }

  public ServiceException(HttpStatus httpStatus, String message){
    super(message);
    this.httpStatus = Optional.ofNullable(httpStatus);
  }

  public ServiceException(HttpStatus httpStatus, List<String> messages){
    this.httpStatus = Optional.ofNullable(httpStatus);
    this.messages = messages;
  }
}
