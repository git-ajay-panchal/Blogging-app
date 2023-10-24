package com.apgroup.blogapp.exceptions;

import com.apgroup.blogapp.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobleExceptionHandler {

    // you can use multiple classes separating by ,
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExHandler(ResourceNotFoundException ex){
        String msg = ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(msg,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    // this class is not created. this is throw by @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidEx(MethodArgumentNotValidException ex){
        Map<String,String> respMap=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            respMap.put(fieldName,message);
        });
        return new ResponseEntity<>(respMap,HttpStatus.BAD_REQUEST);
    }
    // getAllErrors() -> returns list of Error Objects
}
