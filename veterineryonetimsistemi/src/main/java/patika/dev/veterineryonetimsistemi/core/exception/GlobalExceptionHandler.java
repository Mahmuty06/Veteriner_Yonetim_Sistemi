package patika.dev.veterineryonetimsistemi.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import patika.dev.veterineryonetimsistemi.core.result.ResultData;
import patika.dev.veterineryonetimsistemi.core.utilies.Msg;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException exception){

    List<String> validationErrorList = exception.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());
    ResultData<List<String>> resultData =  new ResultData<>(false, Msg.VALIDATE_ERROR,"400",validationErrorList);
    return new ResponseEntity<>(resultData, HttpStatus.BAD_REQUEST);

}


}
