package com.example.Data.Access.Object.demo.exceptionHandler;


import com.example.Data.Access.Object.demo.StackTraceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
      private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

   /* @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handlerAppException(UserException ex) throws Exception {
        logger.error(String.format("message: %s | stackTrace: %s", ex.getMessage(), StackTraceUtil.getStackTraceAsString(ex)));

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString(new ErrorDto(ex.getMessage(), 400));
        return ResponseEntity.badRequest().body(responseJson);

    }*/

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerGlobalException(Exception ex) throws Exception {
        logger.error(String.format("message: %s | stackTrace: %s", ex.getMessage(), StackTraceUtil.getStackTraceAsString(ex)));

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString("Что то пошло не так.");
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(responseJson);

    }

}
